package com.hotdog.saas.domain.repository;

import com.hotdog.saas.domain.model.Menu;

import java.util.List;

public interface MenuRepository {

    List<Menu> list(Menu menu);

    Integer save(Menu menu);

    Menu findById(Long id);

    Long exists(Long id);

    Long existsByName(String name);

    Long existsByPermission(String permission);

    Integer modify(Menu menu);

    Integer remove(Long id, String operator);

}
