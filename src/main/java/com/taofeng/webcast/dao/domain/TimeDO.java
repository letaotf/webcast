package com.taofeng.webcast.dao.domain;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * <p>时间类型表</p>
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/2/22 下午2:59
 * @since V1.0
 */
@Data
public class TimeDO {
    /**
     * 时间id
     */
    private Long timeId;
    /**
     * 时间类型
     */
    private Long timeType;
    /**
     * 描述id
     */
    private Long description;
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