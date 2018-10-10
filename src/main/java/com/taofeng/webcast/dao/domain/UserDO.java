package com.taofeng.webcast.dao.domain;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class UserDO {
    /**
     * 用户id
     */
    private Long userId;

    /**
     * 用户编号
     */
    private String userNo;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 用户头像
     */
    private String userHeadImg;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 用户类型
     */
    private Integer userType;

    /**
     * 用户联系电话
     */
    private String userTel;

    /**
     * 用户性别
     */
    private Integer userSex;

    /**
     * 该条记录扥状态
     */
    private Integer status;

    /**
     * 记录的创建时间
     */
    private LocalDateTime gmtCreate;

    /**
     * 记录的修改时间
     */
    private LocalDateTime gmtModified;

    public String toString() {
        return userId + "," + userName + "," + userType + "," + userTel + "," + userSex + "," + status + "," + gmtCreate + "," + gmtModified;
    }
}