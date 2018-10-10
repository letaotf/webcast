package com.taofeng.webcast.common.DTO;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>公告信息</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/5/28 上午9:02
 * @since V1.0
 */
@Data
public class NoticeDTO {

    @ApiModelProperty(notes = "标题")
    private String title;

    @ApiModelProperty(notes = "标题内容")
    private String content;
}
