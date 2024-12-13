package com.hotdog.saas.domain.service.impl;

import com.hotdog.saas.domain.service.PasswordService;
import com.hotdog.saas.domain.utils.SignUtils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class PasswordServiceImpl implements PasswordService {
    @Override
    public String generatorPassword(String password, String salt) {
        return SignUtils.md5(password + salt);
    }

    @Override
    public Boolean checkPassword(String oldPassword, String password, String salt) {
        String hashPassword = SignUtils.md5(password + salt);
        return StringUtils.equals(hashPassword, oldPassword);
    }
}
