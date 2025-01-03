package com.hotdog.saas.domain.enums.education;

import com.hotdog.saas.domain.enums.EnumInterface;

import java.util.Objects;
import java.util.stream.Stream;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CourseClassPersonTypeEnum implements EnumInterface<Integer> {
    UNKNOWN(-1, "未知"),
    TEACHER(1, "教师"),
    STUDENT(2, "正式学员"),
    TEMP_STUDENT(3, "试课学员"),
    ;
    private final Integer code;
    private final String desc;

    @Override
    public Boolean exist(Integer code) {
        return Stream.of(values()).anyMatch(s -> s.getCode().equals(code));
    }

    public static CourseClassPersonTypeEnum codeToEnum(Integer code) {
        for (CourseClassPersonTypeEnum menuEnum : values()) {
            if (Objects.equals(menuEnum.getCode(), code)) {
                return menuEnum;
            }
        }
        return UNKNOWN;
    }

}
