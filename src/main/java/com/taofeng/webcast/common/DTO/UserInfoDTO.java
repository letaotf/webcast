package com.taofeng.webcast.common.DTO;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>用户信息详情</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/5/11 下午4:14
 * @since V1.0
 */
@Data
public class UserInfoDTO {

    @ApiModelProperty(notes = "用户编号")
    private String userNo;

    @ApiModelProperty(notes = "用户姓名")
    private String userName;

    @ApiModelProperty(notes = "用户头像")
    private String userHeadImg;

    @ApiModelProperty(notes = "用户联系电话")
    private String userTel;

    @ApiModelProperty(notes = "用户性别")
    private Integer userSex;

    @ApiModelProperty(notes = "用户性别描述")
    private String sexDesc;

    @ApiModelProperty(notes = "身份证")
    private String identityCard;

    @ApiModelProperty(notes = "身份证照片路径")
    private String cardImgPath;

    @ApiModelProperty(notes = "节目详情id")
    private Long networkBroadcastTypeDetailId;

    @ApiModelProperty(notes = "节目详情节目名称")
    private String networkBroadcastTypeName;
}
