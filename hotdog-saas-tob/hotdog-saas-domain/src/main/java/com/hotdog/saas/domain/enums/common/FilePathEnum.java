package com.hotdog.saas.domain.enums.common;

import com.hotdog.saas.domain.enums.EnumInterface;
import org.apache.commons.lang3.StringUtils;
import java.util.stream.Stream;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FilePathEnum implements EnumInterface<Integer> {
    UNKNOWN(-1, "unknown"),
    TMP(1, "tmp"),
    FORMAL(2, "formal"),
    ;

    private final Integer code;
    private final String desc;

    @Override
    public Boolean exist(Integer code) {
        return Stream.of(values()).anyMatch(s -> s.getCode().equals(code));
    }

    public static FilePathEnum descToEnum(String desc) {
        for (FilePathEnum dataBaseEnum : values()) {
            if (StringUtils.equals(dataBaseEnum.getDesc(), desc)) {
                return dataBaseEnum;
            }
        }
        return UNKNOWN;
    }

}
