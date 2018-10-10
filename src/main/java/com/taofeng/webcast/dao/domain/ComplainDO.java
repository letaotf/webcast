package com.taofeng.webcast.dao.domain;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * <p>投诉记录表</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/2/22 下午1:45
 * @since V1.0
 */
@Data
public class ComplainDO {

    /**
     * 投诉id
     */
    private Long complainId;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 投诉人id
     */
    private Long personByReportedId;

    /**
     * 投诉原因
     */
    private String reason;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 创建时间
     */
    private LocalDateTime gmtCreate;

    /**
     * 修改时间
     */
    private LocalDateTime gmtModified;

}