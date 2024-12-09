package com.hotdog.saas.domain.enums.common;

import com.hotdog.saas.domain.enums.EnumInterface;

import java.util.stream.Stream;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusEnum implements EnumInterface {
    ENABLE(0, "启用"),
    DISABLE(1, "停用"),
    ;
    private final Integer code;
    private final String desc;

    @Override
    public Boolean exist(Integer code) {
        return Stream.of(values()).anyMatch(s -> s.getCode().equals(code));
    }
}
