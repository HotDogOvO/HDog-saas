package com.hotdog.saas.domain.enums.education;

import com.hotdog.saas.domain.enums.EnumInterface;

import java.util.Objects;
import java.util.stream.Stream;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CourseClassStatusEnum implements EnumInterface<Integer> {
    UNKNOWN(0, "未知"),
    WAITING_START(1, "待开班"),
    STARTING(2, "开班中"),
    FINISH(3, "结业"),
    CANCEL(9, "取消"),
    ;
    private final Integer code;
    private final String desc;

    @Override
    public Boolean exist(Integer code) {
        return Stream.of(values()).anyMatch(s -> s.getCode().equals(code));
    }

    public static CourseClassStatusEnum codeToEnum(Integer code) {
        for (CourseClassStatusEnum menuEnum : values()) {
            if (Objects.equals(menuEnum.getCode(), code)) {
                return menuEnum;
            }
        }
        return UNKNOWN;
    }

}
