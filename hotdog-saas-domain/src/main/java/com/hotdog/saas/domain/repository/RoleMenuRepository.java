package com.hotdog.saas.domain.repository;

import com.hotdog.saas.domain.model.RoleMenu;

import java.util.List;
import java.util.Set;

public interface RoleMenuRepository {

    Integer save(RoleMenu roleMenu);

    List<RoleMenu> findByRoleId(Long roleId);

    Set<RoleMenu> findByRoleIdList(List<Long> roleIdList);

    Integer removeByRoleId(Long roleId);

}
