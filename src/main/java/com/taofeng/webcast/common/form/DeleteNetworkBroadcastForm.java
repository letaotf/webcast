package com.taofeng.webcast.common.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>删除节目</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/3/13 下午2:15
 * @since V1.0
 */
@Data
public class DeleteNetworkBroadcastForm {

    @ApiModelProperty(notes = "节目类型详情ID")
    private Long networkBroadcastTypeDetailId;

}
