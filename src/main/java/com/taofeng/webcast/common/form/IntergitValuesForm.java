package com.taofeng.webcast.common.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>诚信值描述</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/3/13 上午11:40
 * @since V1.0
 */
@Data
public class IntergitValuesForm extends PageForm{

    @ApiModelProperty(notes = "用户姓名")
    private String userName;

    @ApiModelProperty(notes = "用户编号")
    private String userNO;

    @ApiModelProperty(notes = "上诚信值")
    private Double upValue;

    @ApiModelProperty(notes = "下诚信值")
    private Double underValue;

    @ApiModelProperty(notes = "用户类型")
    private Integer userType;
}
