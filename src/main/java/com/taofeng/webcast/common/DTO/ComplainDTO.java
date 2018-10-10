package com.taofeng.webcast.common.DTO;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>投诉表单</p>
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/3/13 下午1:13
 * @since V1.0
 */
@Data
public class ComplainDTO {

    @ApiModelProperty(notes = "投诉id")
    private Long complainId;

    @ApiModelProperty(notes = "投诉姓名")
    private String userName;

    @ApiModelProperty(notes = "被投诉人姓名")
    private String defendantUserName;

    @ApiModelProperty(notes = "投诉人联系电话")
    private String userTel;

    @ApiModelProperty(notes = "被投诉人电话")
    private String defendantUserTel;

    @ApiModelProperty(notes = "投诉编号")
    private String userNo;

    @ApiModelProperty(notes = "被投诉人编号")
    private String defendantUserNo;

    @ApiModelProperty(notes = "原因")
    private String reason;

    @ApiModelProperty(notes = "投诉等级")
    private Integer complainRank;

    @ApiModelProperty(notes = "投诉等级")
    private String  complainRankDesc;

    @ApiModelProperty(notes = "投诉时间")
    private String complainDate;

    @ApiModelProperty(notes = "操作状态")
    private Integer operatorType;
}
