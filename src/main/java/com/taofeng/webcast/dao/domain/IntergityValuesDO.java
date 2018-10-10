package com.taofeng.webcast.dao.domain;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * <p>诚信表</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/2/22 下午2:14
 * @since V1.0
 */
@Data
public class IntergityValuesDO {

    /**
     * 诚信id
     */
    private Long intergityId;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 价值
     */
    private Double valueNum;

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