package com.hotdog.saas.domain.repository;

import com.hotdog.saas.domain.model.RoleMenu;

import java.util.List;

public interface RoleMenuRepository {

    Integer save(RoleMenu roleMenu);

    List<RoleMenu> findByRoleId(Long roleId);

    Integer removeByRoleId(Long roleId);

}
