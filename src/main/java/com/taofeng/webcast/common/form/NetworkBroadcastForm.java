package com.taofeng.webcast.common.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>网络节目表单</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/3/13 下午2:13
 * @since V1.0
 */
@Data
public class NetworkBroadcastForm extends PageForm{

    @ApiModelProperty(notes = "节目类型详情ID")
    private Long typeId;
}
