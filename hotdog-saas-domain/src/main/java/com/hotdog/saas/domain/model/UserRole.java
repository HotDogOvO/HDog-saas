package com.hotdog.saas.domain.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UserRole {

    private Long userId;

    private Long roleId;

    private List<Long> roleIdList;
}
