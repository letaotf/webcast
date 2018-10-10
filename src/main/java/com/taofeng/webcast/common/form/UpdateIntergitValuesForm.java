package com.taofeng.webcast.common.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * <p>修改诚信值</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/3/13 上午11:42
 * @since V1.0
 */
@Data
public class UpdateIntergitValuesForm {

    @ApiModelProperty(notes = "诚信值ID")
    @NotNull
    private Long intergityId;

    @ApiModelProperty(notes = "诚信值")
    @NotNull
    private Double value;
}
