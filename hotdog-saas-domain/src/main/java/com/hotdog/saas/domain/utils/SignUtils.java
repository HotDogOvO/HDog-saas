package com.hotdog.saas.domain.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

public class SignUtils {

    public static String generatorAppSecret() {
        String uuid = UUID.randomUUID().toString().replace("-", "");
        return sha256(uuid);
    }

    private static String sha256(String str) {
        try {
            // 获取 SHA-256 的 MessageDigest 实例
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            // 将输入字符串转换为字节数组
            byte[] hashBytes = digest.digest(str.getBytes());

            // 将字节数组转换为十六进制字符串
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            // 如果算法不存在
            throw new RuntimeException("SHA-256 algorithm not available", e);
        }
    }

    public static void main(String[] args) {
        String str = "493647a3daae421e9397e125e17cb942";
        System.out.println(sha256(str));
    }

}
