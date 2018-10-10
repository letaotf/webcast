package com.taofeng.webcast.common.util;

import java.util.UUID;

/**
 * <p>生成随机数</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/5/6 下午6:00
 * @since V1.0
 */
public class UUIDUtils {

    public static String generateUUID() {
        String s = UUID.randomUUID().toString();
        return s.substring(0, 8) + s.substring(9, 13) + s.substring(14, 18) + s.substring(19, 23) + s.substring(24);
    }
}
