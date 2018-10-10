package com.taofeng.webcast.common.DTO;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>投诉记录</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/5/10 下午4:58
 * @since V1.0
 */
@Data
public class ComplainRecordDTO implements Serializable{


    private static final long serialVersionUID = -1062223155249455590L;

    @ApiModelProperty(notes = "扣分项")
    private Double deductValue;

    @ApiModelProperty(notes = "扣分原因")
    private String Reason;

    @ApiModelProperty(notes = "扣分时间")
    private String time;

}

