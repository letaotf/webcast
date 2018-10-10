package com.taofeng.webcast.common.DTO;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>登录用户信息</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/4/27 下午9:45
 * @since V1.0
 */
@Data
public class UserSessionDTO {

    @ApiModelProperty(value = "用户ID")
    private Long userId;

    @ApiModelProperty(value = "用户名称")
    private String userName;
}
