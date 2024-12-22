package com.hotdog.saas.domain.enums.common;

import com.hotdog.saas.domain.enums.EnumInterface;

import java.util.stream.Stream;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DeleteEnum implements EnumInterface<Integer> {
    NO(0, "未删除"),
    YES(1, "已删除"),
    ;
    private final Integer code;
    private final String desc;

    @Override
    public Boolean exist(Integer code) {
        return Stream.of(values()).anyMatch(s -> s.getCode().equals(code));
    }
}
