package com.taofeng.webcast.common.util;

import java.util.Random;
import java.util.UUID;

/**
 * <p>描述</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/5/6 下午6:06
 * @since V1.0
 */
public class UUIDGeneratorUtil {
    public UUIDGeneratorUtil() {
    }

    public static String getUUID() {
        UUID firstUUID = UUID.randomUUID();
        UUID secondUUID = UUID.randomUUID();
        Random random = new Random();
        int index = random.nextInt(20);
        String str = firstUUID.toString().substring(0, index) + secondUUID.toString().substring(index);
        str = str.replace("-", "").toUpperCase();
        System.out.println(str);
        return str;
    }
}
