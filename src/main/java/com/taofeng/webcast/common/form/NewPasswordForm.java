package com.taofeng.webcast.common.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>描述</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/5/28 上午11:10
 * @since V1.0
 */
@Data
public class NewPasswordForm {

    @ApiModelProperty(notes = "用户联系方式")
    private String tel;

    @ApiModelProperty(notes = "新的密码")
    private String password;
}
