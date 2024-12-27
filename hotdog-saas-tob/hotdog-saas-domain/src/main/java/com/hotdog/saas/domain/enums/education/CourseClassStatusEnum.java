package com.hotdog.saas.domain.enums.education;

import com.google.common.collect.Lists;

import com.hotdog.saas.domain.enums.EnumInterface;

import java.util.List;
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

    /**
     * 校验班级信息是否可以修改
     * @param code 班级状态
     * @return 是否可以修改
     */
    public static boolean canUpdateClass(Integer code){
        CourseClassStatusEnum courseClassStatusEnum = codeToEnum(code);
        return !courseClassStatusEnum.equals(WAITING_START);
    }

    /**
     * 校验课程下的班级状态，用于验证课程是否可以删除
     * @param code 班级状态
     * @return 能否删除
     */
    public static boolean cantDeleteCourse(Integer code){
        CourseClassStatusEnum courseClassStatusEnum = codeToEnum(code);
        return cantDeleteCourseStatusList().contains(courseClassStatusEnum);
    }

    public static List<CourseClassStatusEnum> cantDeleteCourseStatusList(){
        return Lists.newArrayList(WAITING_START, STARTING);
    }

}
