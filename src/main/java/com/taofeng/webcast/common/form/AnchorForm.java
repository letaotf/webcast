package com.taofeng.webcast.common.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>网络主播管理的表单 </p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/3/12 下午3:51
 * @since V1.0
 */
@Data
public class AnchorForm extends PageForm{

    @ApiModelProperty(notes = "用户姓名")
    private String userName;

    @ApiModelProperty(notes = "用户编号")
    private String userNO;

    @ApiModelProperty(notes = "申请时间")
    private String date;

    @ApiModelProperty(notes = "审核状态")
    private Integer auditStatus;
}
