package com.hotdog.saas.domain.enums.education;

import com.hotdog.saas.domain.enums.EnumInterface;

import java.util.Objects;
import java.util.stream.Stream;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CourseClassEnrollStatusEnum implements EnumInterface<Integer> {
    UNKNOWN(-1, "未知"),
    ENROLL_SUCCESS(1, "报名成功"),
    CLASS_ASSIGNING(2, "班级分配中"),
    CLASS_ASSIGN_SUCCESS(3, "班级分配完成"),
    ;
    private final Integer code;
    private final String desc;

    @Override
    public Boolean exist(Integer code) {
        return Stream.of(values()).anyMatch(s -> s.getCode().equals(code));
    }

    public static CourseClassEnrollStatusEnum codeToEnum(Integer code) {
        for (CourseClassEnrollStatusEnum menuEnum : values()) {
            if (Objects.equals(menuEnum.getCode(), code)) {
                return menuEnum;
            }
        }
        return UNKNOWN;
    }

}
