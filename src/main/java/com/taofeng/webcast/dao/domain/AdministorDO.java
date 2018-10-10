package com.taofeng.webcast.dao.domain;

import lombok.Data;

import java.util.Date;

@Data
public class AdministorDO {
    /**
     * 管理员id
     */
    private Long administorId;

    /**
     * 管理员名称
     */
    private String administorName;

    /**
     * 管理员登录密码
     */
    private String password;

    /**
     * 管理员权限
     */
    private Integer authority;

    /**
     * 管理员联系方式
     */
    private String administorTel;

    /**
     * 管理员性别
     */
    private Integer administorSex;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 生成时间
     */
    private Date gmtCreate;

    /**
     * 修改时间
     */
    private Date gmtModified;
}