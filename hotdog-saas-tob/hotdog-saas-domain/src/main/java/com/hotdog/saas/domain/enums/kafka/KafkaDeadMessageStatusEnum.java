package com.hotdog.saas.domain.enums.kafka;

import com.google.common.collect.Lists;

import com.hotdog.saas.domain.enums.EnumInterface;

import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.stream.Stream;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum KafkaDeadMessageStatusEnum implements EnumInterface<Integer> {
    NOT_RETRY(0, "未重试"),
    RETRY_SUCCESS(1, "重试成功"),
    RETRY_FAIL(2, "重试失败"),
    ;
    private final Integer code;
    private final String desc;

    @Override
    public Boolean exist(Integer code) {
        return Stream.of(values()).anyMatch(s -> s.getCode().equals(code));
    }

    public static List<KafkaDeadMessageStatusEnum> needRetryStatus() {
        return Lists.newArrayList(NOT_RETRY, RETRY_FAIL);
    }
}
