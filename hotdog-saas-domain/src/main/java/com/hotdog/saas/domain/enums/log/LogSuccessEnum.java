package com.hotdog.saas.domain.enums.log;

import com.hotdog.saas.domain.enums.EnumInterface;

import java.util.Objects;
import java.util.stream.Stream;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum LogSuccessEnum implements EnumInterface {
    UNKNOWN(-1, "未知"),
    SUCCESS(0, "成功"),
    FAIL(1, "失败"),
    ;
    private final Integer code;
    private final String desc;

    @Override
    public Boolean exist(Integer code) {
        return Stream.of(values()).anyMatch(s -> s.getCode().equals(code));
    }

    public static LogSuccessEnum codeToEnum(Integer code) {
        for (LogSuccessEnum menuEnum : values()) {
            if (Objects.equals(menuEnum.getCode(), code)) {
                return menuEnum;
            }
        }
        return UNKNOWN;
    }

}
