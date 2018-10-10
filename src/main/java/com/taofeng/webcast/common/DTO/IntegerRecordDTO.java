package com.taofeng.webcast.common.DTO;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * <p>诚信记录</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/5/10 下午4:57
 * @since V1.0
 */
@Data
public class IntegerRecordDTO implements Serializable{

    private static final long serialVersionUID = -6354847427975981128L;

    @ApiModelProperty(notes = "诚信值")
    private Double values;

    @ApiModelProperty(notes = "扣分记录")
    private List<ComplainRecordDTO> complainRecordDTOS;
}
