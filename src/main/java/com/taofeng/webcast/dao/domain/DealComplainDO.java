package com.taofeng.webcast.dao.domain;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * <p>投诉详情</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/2/22 下午1:49
 * @since V1.0
 */
@Data
public class DealComplainDO {

    /**
     * 投诉人详情id
     */
    private Long dealComplainId;

    /**
     * 投诉id
     */
    private Long complainId;

    /**
     * 操作人id
     */
    private Long operatorId;

    /**
     * 投诉等级
     */
    private Integer complainRank;

    /**
     * 操作人姓名
     */
    private String operatorName;

    /**
     * 操作类型
     */
    private Integer operatorType;

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