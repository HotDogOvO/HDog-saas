package com.hotdog.saas.domain.service;

public interface PasswordService {

    /**
     * 生成密码
     *
     * @param password 密码
     * @param salt     盐
     * @return 加密密码
     */
    String generatorPassword(String password, String salt);

    /**
     * 校验密码
     *
     * @param oldPassword 原密码
     * @param password    待校验的密码
     * @param salt        盐
     * @return boolean
     */
    Boolean checkPassword(String oldPassword, String password, String salt);

}
