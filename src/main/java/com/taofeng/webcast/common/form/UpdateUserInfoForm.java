package com.taofeng.webcast.common.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>更新用户信息</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/5/11 下午4:38
 * @since V1.0
 */
@Data
public class UpdateUserInfoForm {

    @ApiModelProperty(notes = "用户类别")
    private Integer userType;

    @ApiModelProperty(notes = "用户ID")
    private Long userId;

    @ApiModelProperty(notes = "用户姓名")
    private String userName;

    @ApiModelProperty(notes = "用户头像")
    private String userHeadImg;

    @ApiModelProperty(notes = "用户联系电话")
    private String userTel;

    @ApiModelProperty(notes = "用户性别")
    private Integer userSex;

    @ApiModelProperty(notes = "节目详情id")
    private Long networkBroadcastTypeDetailId;
}
