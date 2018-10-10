package com.taofeng.webcast.common.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>资产表单</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/3/13 下午2:46
 * @since V1.0
 */
@Data
public class AssetManagementForm extends PageForm{

    @ApiModelProperty(notes = "资产编号")
    private String assetNo;

    @ApiModelProperty(notes = "用户编号")
    private String userNo;
}
