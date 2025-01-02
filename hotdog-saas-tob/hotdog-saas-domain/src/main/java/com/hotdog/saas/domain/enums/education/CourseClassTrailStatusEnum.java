package com.hotdog.saas.domain.enums.education;

import com.hotdog.saas.domain.enums.EnumInterface;

import java.util.Objects;
import java.util.stream.Stream;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 试课状态枚举
 * @author hotdog
 * @date 2025/1/2 16:53
 */
@Getter
@AllArgsConstructor
public enum CourseClassTrailStatusEnum implements EnumInterface<Integer> {
    UNKNOWN(0, "未知"),
    REGISTERED(1, "已报名"),
    ATTEND_CLASS(2,"已上课"),
    UN_ATTEND_CLASS(3,"未上课"),
    ;
    private final Integer code;
    private final String desc;

    @Override
    public Boolean exist(Integer code) {
        return Stream.of(values()).anyMatch(s -> s.getCode().equals(code));
    }

    public static CourseClassTrailStatusEnum codeToEnum(Integer code) {
        for (CourseClassTrailStatusEnum menuEnum : values()) {
            if (Objects.equals(menuEnum.getCode(), code)) {
                return menuEnum;
            }
        }
        return UNKNOWN;
    }

}
