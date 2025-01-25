package com.hotdog.saas.matrix.domain.utils;


import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.UUID;

public class SignUtils {

    /**
     * 生成租户secret，aes+base64加密
     *
     * @param appSecret 系统秘钥
     * @return 租户secret
     */
    public static String generatorAppSecret(String appSecret) throws Exception {
        return aesEncrypt(uuid(), appSecret);
    }

    /**
     * 生成一个UUID
     *
     * @return uuid
     */
    public static String uuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 签名算法
     * （appSecret + param + appSecret）进行sha256加密
     *
     * @param param     待加密的串
     * @param appSecret 秘钥
     * @return 签名
     */
    public static String sign(String param, String appSecret) {
        String signStr = appSecret + param + appSecret;
        return sha256(signStr);
    }

    /**
     * sha256加密
     *
     * @param str 待加密串
     * @return 加密串
     */
    public static String sha256(String str) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            return hashString(digest, str);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 algorithm not available", e);
        }
    }

    public static String md5(String str) {
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            return hashString(digest, str);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 algorithm not available", e);
        }
    }

    private static String hashString(MessageDigest digest, String str) {
        byte[] hashBytes = digest.digest(str.getBytes());
        StringBuilder hexString = new StringBuilder();
        for (byte b : hashBytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    /**
     * 使用AES对字符串加密
     *
     * @param str    utf8编码的字符串
     * @param secret 密钥（16字节）
     * @return 加密结果（base64）
     * @throws Exception
     */
    public static String aesEncrypt(String str, String secret) throws Exception {
        if (str == null || secret == null) {
            return null;
        }
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), "AES"));
        byte[] bytes = cipher.doFinal(str.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(bytes);
    }

    /**
     * 使用AES对数据解密
     *
     * @param str    base64加密串
     * @param secret 密钥（16字节）
     * @return 解密结果
     * @throws Exception
     */
    public static String aesDecrypt(String str, String secret) throws Exception {
        SecretKeySpec keySpec = new SecretKeySpec(secret.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, keySpec);

        // 从 Base64 解码并解密
        byte[] decoded = Base64.getDecoder().decode(str);
        byte[] decrypted = cipher.doFinal(decoded);
        return new String(decrypted);
    }

    public static void main(String[] args) throws Exception {
        String str = "493647a3daae421e9397e125e17cb942";
        String key = "1234567890abcdef";
        String enc = aesEncrypt(str, key);
        System.out.println(enc);
        System.out.println(aesDecrypt(enc, key));
    }

}
