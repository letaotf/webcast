package com.taofeng.webcast.common.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>投诉管理列表</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/3/13 下午1:06
 * @since V1.0
 */
@Data
public class ComplainForm extends PageForm{

    @ApiModelProperty(notes = "投诉人用户编号")
    private String userNo;

    @ApiModelProperty(notes = "被投诉人用户编号")
    private String defendantUserNo;

}
