package com.hotdog.saas.domain.repository;

import com.hotdog.saas.domain.model.LoginLog;
import com.hotdog.saas.domain.model.page.PageRequest;
import com.hotdog.saas.domain.model.page.PageResponse;

import java.util.List;

public interface LoginLogRepository {

    Integer save(LoginLog loginLog);

    PageResponse<List<LoginLog>> listPage(LoginLog loginLog, PageRequest pageRequest);
}
