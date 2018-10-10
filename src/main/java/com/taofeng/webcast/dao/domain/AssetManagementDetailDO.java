package com.taofeng.webcast.dao.domain;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * <p>资产详情表</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/2/22 下午1:37
 * @since V1.0
 */

@Data
public class AssetManagementDetailDO {

    /**
     * 资产详细id
     */
    private Long assetDetailId;

    /**
     * 资产Id
     */
    private Long assetId;

    /**
     * day描述id
     */
    private Long dayDescription;

    /**
     * month描述id
     */
    private Long monthDescription;

    /**
     * year描述id
     */
    private Long yearDescription;

    /**
     * 鱼丸的总价
     */
    private Double fishBallMoney;

    /**
     * 鲜花的总价
     */
    private Double flowerMoney;

    /**
     * 火箭的总价
     */
    private Double rocketMoney;

    /**
     * 总价
     */
    private Double allMoney;

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