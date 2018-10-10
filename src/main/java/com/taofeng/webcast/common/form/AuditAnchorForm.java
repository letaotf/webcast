package com.taofeng.webcast.common.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * <p>审核网络主播表单</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/3/12 下午4:20
 * @since V1.0
 */
@Data
public class AuditAnchorForm {

    @ApiModelProperty(notes = "用户id")
    @NotNull
    private Long userId;

    @ApiModelProperty(notes = "审核状态")
    @NotNull
    private Integer auditStatus;
}
