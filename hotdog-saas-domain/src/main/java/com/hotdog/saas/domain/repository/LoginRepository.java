package com.hotdog.saas.domain.repository;

import com.hotdog.saas.domain.model.Login;

public interface LoginRepository {

    Login findLoginUser(String username);
}
