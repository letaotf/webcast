package com.taofeng.webcast.common.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * <p>删除商品的表单</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/3/12 下午5:26
 * @since V1.0
 */
@Data
public class DeleteGoodsForm {

    @ApiModelProperty(notes = "商品Id",required = true)
    @NotNull
    private Long goodsId;
}
