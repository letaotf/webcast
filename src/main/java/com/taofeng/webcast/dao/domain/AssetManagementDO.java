package com.taofeng.webcast.dao.domain;

import lombok.Data;

import java.util.Date;

@Data
public class AssetManagementDO {
    /**
     * 资产id
     */
    private Long assetId;

    /**
     * 资产编号
     */
    private String assetNo;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 鱼丸的数量
     */
    private Integer fishBallNum;

    /**
     * 鱼丸的总价
     */
    private Double fishBallValue;

    /**
     * 鲜花的数量
     */
    private Integer flowerNum;

    /**
     * 鲜花的价格
     */
    private Double flowerValue;

    /**
     * 火箭的数量
     */
    private Integer rocketNum;

    /**
     * 火箭的价格
     */
    private Double rocketValue;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 修改时间
     */
    private Date gmtModified;

}