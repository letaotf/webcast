package com.taofeng.webcast.dao.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NetworkBroadcastTypeDetailsDO {
    /**
     * 节目类型详情ID
     */
    private Long networkBroadcastTypeDetailId;

    /**
     * 节目类型ID
     */
    private Long typeId;

    /**
     * 节目类型编号
     */
    private String networkTypeNo;

    /**
     * 节目详细名称
     */
    private String typeDetailName;

    /**
     * 节目详细介绍
     */
    private String briefIntroduction;

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