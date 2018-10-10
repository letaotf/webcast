package com.taofeng.webcast.common.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>用来接收前端传递过来的,关于用户信息的数据</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/2/26 下午1:49
 * @since V1.0
 */
@Data
@ApiModel(value = "用户信息")
public class UserForm {

    @ApiModelProperty(value = "用户id", required = true)
    private Long userId;

    @ApiModelProperty(value = "用户名称",required = true)
    private String userName;

    @ApiModelProperty(value = "用户密码",required = true)
    private String password;

    @ApiModelProperty(value = "用户类型",required = false)
    private Integer userType;

    @ApiModelProperty(value = "用户的联系方式",required = false)
    private String userTel;

    @ApiModelProperty(value = "用户性别",required = false)
    private Integer userSex;

    @ApiModelProperty(notes = "身份证")
    private String identityCard;

    @ApiModelProperty(notes = "身份证照片路径")
    private String cardImgPath;

    @ApiModelProperty(notes = "节目详情id")
    private Long typeId;

    @ApiModelProperty(notes = "节目详细描述")
    private String typeDetailName;
}
