package com.hotdog.saas.domain.model;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RoleMenu {

    private Long roleId;

    private Long menuId;

    private List<Long> menuIdList;
}
