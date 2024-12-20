package com.hotdog.saas.domain.repository;

import com.hotdog.saas.domain.model.WechatApp;
import com.hotdog.saas.domain.model.page.PageRequest;
import com.hotdog.saas.domain.model.page.PageResponse;

import java.util.List;

public interface WechatAppRepository {

    Integer save(WechatApp wechatApp);

    PageResponse<List<WechatApp>> listPage(WechatApp wechatApp, PageRequest pageRequest);

    WechatApp findById(Long id);

    Long exists(Long id);

    Long existsByWechatAppId(String wechatAppId);

    Integer modify(WechatApp wechatApp);

    Integer remove(Long id, String operator);
}
