package com.example.testBootOne.utils;

import org.yaml.snakeyaml.util.UriEncoder;
import org.apache.commons.codec.digest.DigestUtils;

public class Md5Util {
    /**
     * 固定salt
     */
    private static final String SALT = "ad!@12VB&*?";

    /**
     * 禁止实例化
     **/
    private Md5Util() {};

    /**
     * Md5加密
     *
     * @param password 密码
     * @return 加密后密码
     */
    public static String md5(String password) {
        return DigestUtils.md5Hex(UriEncoder.encode(password));
    }


    /**
     * 明文密码根据固定盐生成MD5，用于用户传输到服务器加密
     *
     * @param password 密码
     * @return 加密后密码
     */
    public static String frontEncryption(String password) {
        String newp = SALT.charAt(3) + SALT.charAt(7) + password + SALT.charAt(2);
        return md5(newp);
    }
}
