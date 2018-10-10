package com.taofeng.webcast.dao.domain;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * <p>网络直播类型</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/2/22 下午2:46
 * @since V1.0
 */
@Data
public class NetworkBroadcastTypeDO {

    /**
     * 类型Id
     */
    private Long typeId;
    /**
     * 类型名称
     */
    private String typeName;
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