package com.barista.util;

import org.springframework.util.DigestUtils;

/**
 * MD5加密
 *
 * @ClassName MD5Util
 * @Author zhaoth
 * @Date 2019/8/10 15:26
 * @Version 1.0
 */
public class MD5Util {
    private static final String SLAT = "f427d8a660ab7993039c1106f86ac5cb";//barista*&/UCC117

    public static String encode(String password) {
        String temp = DigestUtils.md5DigestAsHex(password.getBytes()) + SLAT;
        return DigestUtils.md5DigestAsHex(temp.getBytes());
    }

}
