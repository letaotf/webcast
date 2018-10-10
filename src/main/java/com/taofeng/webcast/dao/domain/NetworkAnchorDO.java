package com.taofeng.webcast.dao.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NetworkAnchorDO {

    /**
     * 网络主笔id
     */
    private Long networkAnchorId;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 审核状态
     */
    private Integer auditStatus;

    /**
     * 身份证
     */
    private String identityCard;

    /**
     * 身份证照片路径
     */
    private String cardImgPath;

    /**
     * 节目详情id
     */
    private Long networkBroadcastTypeDetailId;

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