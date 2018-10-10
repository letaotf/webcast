package com.taofeng.webcast.common.DTO;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * <p>诚信值DTO</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/3/13 上午11:41
 * @since V1.0
 */
@Data
public class IntergitValuesDTO {

    @ApiModelProperty(notes = "诚信值id")
    @NotNull
    private Long intergityId;

    @ApiModelProperty(notes = "用户id")
    @NotNull
    private Long userId;

    @ApiModelProperty(notes = "用户编号")
    @NotNull
    private String userNo;

    @ApiModelProperty(notes = "用户名称")
    @NotNull
    private String userName;

    @ApiModelProperty(notes = "用户联系电话")
    @NotNull
    private String userTel;

    @ApiModelProperty(notes = "用户类型")
    @NotNull
    private Integer userType;

    @ApiModelProperty(notes = "用户描述")
    @NotNull
    private String userTypeDesc;

    @ApiModelProperty(notes = "诚信值")
    @NotNull
    private Double value;

}
