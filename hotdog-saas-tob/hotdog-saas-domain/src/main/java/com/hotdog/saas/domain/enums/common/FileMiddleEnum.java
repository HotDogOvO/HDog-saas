package com.hotdog.saas.domain.enums.common;

import com.hotdog.saas.domain.enums.EnumInterface;

import org.apache.commons.lang3.StringUtils;

import java.util.stream.Stream;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FileMiddleEnum implements EnumInterface<Integer> {

    UNKNOWN(-1, "unknown"),
    MINIO(1, "minio"),
    ;

    private final Integer code;
    private final String desc;

    @Override
    public Boolean exist(Integer code) {
        return Stream.of(values()).anyMatch(s -> s.getCode().equals(code));
    }

    public static FileMiddleEnum descToEnum(String desc) {
        for (FileMiddleEnum dataBaseEnum : values()) {
            if (StringUtils.equals(dataBaseEnum.getDesc(), desc)) {
                return dataBaseEnum;
            }
        }
        return UNKNOWN;
    }

}
