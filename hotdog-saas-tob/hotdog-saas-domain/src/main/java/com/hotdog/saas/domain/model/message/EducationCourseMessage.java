package com.hotdog.saas.domain.model.message;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EducationCourseMessage extends BaseMessage {

    private List<Long> startScheduleIdList;

    private List<Long> endScheduleIdList;

    private Long timestamp;

}
