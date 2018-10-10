package com.taofeng.webcast.common.Enum;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>描述</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/3/5 下午8:50
 * @since V1.0
 */
@Getter
@AllArgsConstructor
public enum RoleEnum {

    COMMON_USER(0,"普通用户"),
    NETWORK_ANCHOR(1,"网络主播"),
    ADMINISTOR(2,"管理员"),;

    private Integer code;
    private String meg;
}
