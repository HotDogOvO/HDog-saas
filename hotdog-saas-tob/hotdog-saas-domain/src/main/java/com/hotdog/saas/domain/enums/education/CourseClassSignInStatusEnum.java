package com.hotdog.saas.domain.enums.education;

import com.hotdog.saas.domain.enums.EnumInterface;

import java.util.Objects;
import java.util.stream.Stream;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CourseClassSignInStatusEnum implements EnumInterface<Integer> {
    UNKNOWN(-1, "未知"),
    SIGN_IN_SUCCESS(1, "签到成功"),
    SIGN_IN_FAIL(2, "签到失败"),
    ;
    private final Integer code;
    private final String desc;

    @Override
    public Boolean exist(Integer code) {
        return Stream.of(values()).anyMatch(s -> s.getCode().equals(code));
    }

    public static CourseClassSignInStatusEnum codeToEnum(Integer code) {
        for (CourseClassSignInStatusEnum menuEnum : values()) {
            if (Objects.equals(menuEnum.getCode(), code)) {
                return menuEnum;
            }
        }
        return UNKNOWN;
    }

}
