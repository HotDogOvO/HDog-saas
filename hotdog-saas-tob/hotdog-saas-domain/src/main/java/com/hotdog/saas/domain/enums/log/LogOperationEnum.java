package com.hotdog.saas.domain.enums.log;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import com.hotdog.saas.domain.constant.Constants;
import com.hotdog.saas.domain.enums.EnumInterface;
import com.hotdog.saas.domain.model.OperationLog;
import com.hotdog.saas.domain.model.message.CanalLogMessage;
import com.hotdog.saas.domain.utils.DateUtils;

import org.apache.commons.lang3.StringUtils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Slf4j
@Getter
@AllArgsConstructor
public enum LogOperationEnum implements EnumInterface<Integer> {
    UNKNOWN(-1, "UNKNOWN", "未知") {
        @Override
        public List<OperationLog> execute(CanalLogMessage canalLogMessage) {
            log.warn("记录操作请求日志异常，操作类型暂不支持：{}，data：{}", this, canalLogMessage);
            return Lists.newArrayList();
        }
    },
    SELECT(1, "SELECT", "查询") {
        @Override
        public List<OperationLog> execute(CanalLogMessage canalLogMessage) {
            return Lists.newArrayList();
        }
    },
    INSERT(2, "INSERT", "创建") {
        @Override
        public List<OperationLog> execute(CanalLogMessage canalLogMessage) {
            return buildLogs(canalLogMessage, this);
        }
    },
    UPDATE(3, "UPDATE", "编辑") {
        @Override
        public List<OperationLog> execute(CanalLogMessage canalLogMessage) {
            List<Map<String, String>> dataList = canalLogMessage.getData();
            List<Map<String, String>> oldDataList = canalLogMessage.getOld();

            return IntStream.range(0, Math.min(dataList.size(), oldDataList.size()))
                    .mapToObj(i -> {
                        Map<String, String> data = dataList.get(i);
                        Map<String, String> oldData = oldDataList.get(i);
                        OperationLog operationLog = LogOperationEnum.buildBasicOperationLog(data, canalLogMessage.getEs(), this);
                        operationLog.setContent(buildContent(data, oldData, this));
                        return operationLog;
                    })
                    .toList();
        }
    },
    DELETE(4, "DELETE", "删除") {
        @Override
        public List<OperationLog> execute(CanalLogMessage canalLogMessage) {
            return buildLogs(canalLogMessage, this);
        }
    },
    ;

    private final Integer code;
    private final String action;
    private final String desc;

    /**
     * 日志记录抽象方法
     *
     * @param canalLogMessage canal通知消息
     * @return 操作日志集合
     */
    public abstract List<OperationLog> execute(CanalLogMessage canalLogMessage);

    @Override
    public Boolean exist(Integer code) {
        return Stream.of(values()).anyMatch(s -> s.getCode().equals(code));
    }

    public static LogOperationEnum getByAction(String action) {
        for (LogOperationEnum operationEnum : values()) {
            if (Objects.equals(operationEnum.getAction(), action)) {
                return operationEnum;
            }
        }
        return UNKNOWN;
    }

    /**
     * 获取租户ID
     * tenant表用ID，其余表用tenantId
     *
     * @param data 字段
     * @return 租户ID
     */
    private static Long getTenantId(Map<String, String> data) {
        return Long.valueOf(data.getOrDefault("tenantId", data.get("id")));
    }

    /**
     * 获取操作人
     *
     * @param data 字段
     * @return 操作人
     */
    private static String getOperator(Map<String, String> data) {
        return data.get("updater");
    }

    /**
     * 构建基础Log信息
     *
     * @param canalLogMessage canal通知消息
     * @param operationEnum   操作类型
     * @return 操作日志集合
     */
    private static List<OperationLog> buildLogs(CanalLogMessage canalLogMessage, LogOperationEnum operationEnum) {
        return canalLogMessage.getData().stream()
                .map(data -> {
                    OperationLog operationLog = buildBasicOperationLog(data, canalLogMessage.getEs(), operationEnum);
                    operationLog.setContent(buildContent(data, null, operationEnum));
                    return operationLog;
                })
                .toList();
    }

    /**
     * 构建基础日志信息
     *
     * @param data          字段
     * @param operationTime 操作时间
     * @param operationEnum 操作类型
     * @return 操作日志
     */
    private static OperationLog buildBasicOperationLog(Map<String, String> data, Long operationTime, LogOperationEnum operationEnum) {
        return OperationLog.builder()
                .tenantId(getTenantId(data))
                .operationTime(DateUtils.getDateByMill(operationTime))
                .operationType(operationEnum.getCode())
                .operator(getOperator(data))
                .creator(getOperator(data))
                .createTime(DateUtils.now())
                .build();
    }

    /**
     * 构建操作详情
     *
     * @param data          字段
     * @param old           原字段
     * @param operationEnum 操作类型
     * @return 操作详情
     */
    private static String buildContent(Map<String, String> data, Map<String, String> old, LogOperationEnum operationEnum) {
        String operator = getOperator(data);
        String action = operationEnum.getDesc();

        if (operationEnum == UPDATE && Objects.nonNull(old)) {
            return String.format("【%s】【%s】【原数据：%s】，【新数据：%s】", operator, action, old, data);
        }
        return String.format("【%s】【%s】【%s】", operator, action, data);
    }

}