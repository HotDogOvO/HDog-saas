package com.hotdog.saas.domain.repository;

import com.hotdog.saas.domain.model.Menu;

import java.util.List;
import java.util.Set;

public interface MenuRepository {

    Long save(Menu menu);

    Menu findById(Long id);

    List<Menu> list(Menu menu);

    List<Menu> findByParentId(Long parentId);

    List<Menu> findByIdList(Set<Long> idList);

    Long exists(Long id);

    Long existsByName(String name);

    Long existsByPermission(String permission);

    Long countByIdList(List<Long> idList);

    Integer modify(Menu menu);

    Integer remove(Long id, String operator);

}
