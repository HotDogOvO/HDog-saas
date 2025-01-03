package com.hotdog.saas.domain.enums.pay;

import com.hotdog.saas.domain.enums.EnumInterface;

import java.util.Objects;
import java.util.stream.Stream;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PayStatusEnum implements EnumInterface<Integer> {
    UNKNOWN(-1, "未知"),
    NOT_PAY(0, "未支付"),
    PAY_SUCCESS(1, "支付成功"),
    PAY_FAIL(9, "支付失败"),
    ;
    private final Integer code;
    private final String desc;

    @Override
    public Boolean exist(Integer code) {
        return Stream.of(values()).anyMatch(s -> s.getCode().equals(code));
    }

    public static PayStatusEnum codeToEnum(Integer code) {
        for (PayStatusEnum menuEnum : values()) {
            if (Objects.equals(menuEnum.getCode(), code)) {
                return menuEnum;
            }
        }
        return UNKNOWN;
    }

}
