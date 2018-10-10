package com.taofeng.webcast.common.DTO;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>网络节目DTO</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/3/13 下午2:16
 * @since V1.0
 */
@Data
public class NetworkBroadcastDTO {

    @ApiModelProperty(notes = "节目类型详情ID")
    private Long networkBroadcastTypeDetailId;

    @ApiModelProperty(notes = "节目类型ID")
    private Long typeId;

    @ApiModelProperty(notes = "节目类型编号")
    private String networkTypeNo;

    @ApiModelProperty(notes = "节目详细名称")
    private String typeDetailName;

    @ApiModelProperty(notes = "节目详细介绍")
    private String briefIntroduction;

    @ApiModelProperty(notes = "创建时间")
    private String gmtCreate;
}
