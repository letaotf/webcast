package com.taofeng.webcast.dao.domain;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * <p>公告</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/2/22 下午2:56
 * @since V1.0
 */
@Data
public class NoticesDO {
    /**
     * 公告id
     */
    private Long noticeId;

    /**
     * 标题
     */
    private String title;

    /**
     * 标题内容
     */
    private String content;

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