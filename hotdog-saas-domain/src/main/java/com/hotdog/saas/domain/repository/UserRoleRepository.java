package com.hotdog.saas.domain.repository;

import com.hotdog.saas.domain.model.UserRole;

import java.util.List;

public interface UserRoleRepository {

    Integer save(UserRole userRole);

    List<UserRole> findByUserId(Long userId);

    Integer removeByUserId(Long userId);
}
