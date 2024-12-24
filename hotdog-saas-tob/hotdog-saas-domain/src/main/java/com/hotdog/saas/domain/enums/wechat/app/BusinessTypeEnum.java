package com.hotdog.saas.domain.enums.wechat.app;

import com.hotdog.saas.domain.enums.EnumInterface;

import java.util.Objects;
import java.util.stream.Stream;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BusinessTypeEnum implements EnumInterface<Integer> {
    UNKNOWN(-1, "未知", "UNKNOWN"),
    EDUCATION(1, "教育类", "EC"),
    SHOP(2, "电商", "SH"),
    ;
    private final Integer code;
    private final String desc;
    private final String businessNoPrefix;

    @Override
    public Boolean exist(Integer code) {
        return Stream.of(values()).anyMatch(s -> s.getCode().equals(code));
    }

    public static BusinessTypeEnum codeToEnum(Integer code) {
        for (BusinessTypeEnum businessTypeEnum : values()) {
            if (Objects.equals(businessTypeEnum.getCode(), code)) {
                return businessTypeEnum;
            }
        }
        return UNKNOWN;
    }

}
