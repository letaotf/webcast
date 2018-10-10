package com.taofeng.webcast.common.DTO;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>网络主播DTO</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/3/12 下午3:50
 * @since V1.0
 */
@Data
public class AnchorDTO {

    @ApiModelProperty(notes = "用户id")
    private Long userId;

    @ApiModelProperty(notes = "用户编号")
    private String userNo;

    @ApiModelProperty(notes = "用户名称")
    private String userName;

    @ApiModelProperty(notes = "用户性别")
    private Integer userSex;

    @ApiModelProperty(notes = "性别描述")
    private String userSexDesc;

    @ApiModelProperty(notes = "用户联系电话")
    private String userTel;

    @ApiModelProperty(notes = "审核状态")
    private Integer auditStatus;

    @ApiModelProperty(notes = "身份证号")
    private String identityCard;

    @ApiModelProperty(notes = "节目详情id")
    private Long networkBroadcastTypeDetailId;

    @ApiModelProperty(notes = "节目详细名称")
    private String typeDetailName;

    @ApiModelProperty(notes = "创建时间")
    private String  gmtCreate;
}
