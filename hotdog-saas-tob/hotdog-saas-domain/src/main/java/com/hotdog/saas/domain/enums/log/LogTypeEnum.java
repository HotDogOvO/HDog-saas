package com.hotdog.saas.domain.enums.log;

import com.hotdog.saas.domain.enums.EnumInterface;

import java.util.Objects;
import java.util.stream.Stream;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum LogTypeEnum implements EnumInterface<Integer> {
    UNKNOWN(-1, "未知"),
    WEB(100, "WEB"),
    ;
    private final Integer code;
    private final String desc;

    @Override
    public Boolean exist(Integer code) {
        return Stream.of(values()).anyMatch(s -> s.getCode().equals(code));
    }

    public static LogTypeEnum codeToEnum(Integer code) {
        for (LogTypeEnum logTypeEnum : values()) {
            if (Objects.equals(logTypeEnum.getCode(), code)) {
                return logTypeEnum;
            }
        }
        return UNKNOWN;
    }

}
