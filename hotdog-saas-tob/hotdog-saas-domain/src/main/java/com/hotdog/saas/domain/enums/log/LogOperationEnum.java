package com.hotdog.saas.domain.enums.log;

import com.hotdog.saas.domain.enums.EnumInterface;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;
import java.util.stream.Stream;

@Getter
@AllArgsConstructor
public enum LogOperationEnum implements EnumInterface<String> {
    UNKNOWN( "UNKNOWN", "未知"),
    SELECT("SELECT", "查询"),
    INSERT("INSERT", "创建"),
    UPDATE( "UPDATE", "编辑"),
    DELETE("DELETE", "删除"),
    ;

    private final String code;
    private final String desc;

    @Override
    public Boolean exist(String code) {
        return Stream.of(values()).anyMatch(s -> s.getCode().equals(code));
    }

    public static LogOperationEnum getByCode(String action) {
        for (LogOperationEnum operationEnum : values()) {
            if (Objects.equals(operationEnum.getCode(), action)) {
                return operationEnum;
            }
        }
        return UNKNOWN;
    }

}