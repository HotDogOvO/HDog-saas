package com.hotdog.saas.domain.repository;

import com.hotdog.saas.domain.model.User;
import com.hotdog.saas.domain.model.page.PageRequest;
import com.hotdog.saas.domain.model.page.PageResponse;

import java.util.List;

public interface UserRepository {

    Integer save(User user);

    PageResponse<List<User>> listPage(User user, PageRequest pageRequest);

    User findById(Long id);

    Long exists(Long id);

    Long existsByUsername(String username);

    Integer modify(User user);

    Integer remove(Long id, String operator);
}
