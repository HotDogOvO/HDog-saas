package com.hotdog.saas.domain.repository;

import com.hotdog.saas.domain.model.Role;
import com.hotdog.saas.domain.model.page.PageRequest;
import com.hotdog.saas.domain.model.page.PageResponse;

import java.util.List;

public interface RoleRepository {

    Integer save(Role role);

    PageResponse<List<Role>> listPage(Role role, PageRequest pageRequest);

    Role findById(Long id);

    List<Role> findByIdList(List<Long> idList);

    Long exists(Long id);

    Long existsByName(String name, Long tenantId);

    Long countByIdList(List<Long> idList);

    Integer modify(Role role);

    Integer remove(Long id, String operator);
}
