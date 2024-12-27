package com.hotdog.saas.domain.enums.education;

import com.hotdog.saas.domain.enums.EnumInterface;

import java.util.Objects;
import java.util.stream.Stream;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EducationBusinessTypeEnum implements EnumInterface<Integer> {
    UNKNOWN(-1, "未知", "UNKNOWN"),
    EDUCATION_COURSE(1, "课程", "EC"),
    EDUCATION_COURSE_CLASS(2, "班级", "ECC"),
    ;
    private final Integer code;
    private final String desc;
    private final String businessPrefix;

    @Override
    public Boolean exist(Integer code) {
        return Stream.of(values()).anyMatch(s -> s.getCode().equals(code));
    }

    public static EducationBusinessTypeEnum codeToEnum(Integer code) {
        for (EducationBusinessTypeEnum businessTypeEnum : values()) {
            if (Objects.equals(businessTypeEnum.getCode(), code)) {
                return businessTypeEnum;
            }
        }
        return UNKNOWN;
    }

}
