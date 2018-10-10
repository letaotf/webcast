package com.taofeng.webcast.controller;

import com.taofeng.webcast.common.DTO.UserInfoDTO;
import com.taofeng.webcast.common.entity.GeneralResult;
import com.taofeng.webcast.common.form.NewPasswordForm;
import com.taofeng.webcast.common.form.UpdateUserInfoForm;
import com.taofeng.webcast.common.form.UserForm;
import com.taofeng.webcast.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>用户登录与注册的控制</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/2/25 下午1:17
 * @since V1.0
 */
@RestController
@RequestMapping("/user")
@Slf4j
@Api(tags = "用户登录、注册的管理[后台]",description = "用户登录、注册")
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping("/login.jsonHtml")
    @ApiOperation(value = "登录",notes = "登录")
    public GeneralResult<String> login(@RequestBody UserForm form){
        return userService.login(form);
    }

    @GetMapping("/logout.json")
    @ApiOperation(value = "退出登录",notes = "退出登录")
    public GeneralResult<Boolean> logout(){
        return userService.logout();
    }

    @ApiOperation(value = "查询人员信息")
    @GetMapping("/queryLoginUser.json")
    public GeneralResult<Map<String,Object>> queryLoginUser() {
        return userService.queryLoginUser();
    }

    @PostMapping("/register.jsonHtml")
    @ApiOperation(value = "注册",notes = "注册")
    public GeneralResult<String> register(@RequestBody UserForm form){
        return userService.register(form);
    }

    @GetMapping("/queryUserInfoDetail.json")
    @ApiOperation(value = "查询用户信息",notes = "查询用户信息")
    public GeneralResult<UserInfoDTO> queryUserInfoDetail(Long userId, Integer userType){
        UserInfoDTO userInfoDTO = userService.queryUserInfoDetail(userId,userType);
        return GeneralResult.success(userInfoDTO);
    }

    @PostMapping("/updateUserInfoDetail.json")
    @ApiOperation(value = "更新用户信息详情",notes = "更新用户信息")
    public GeneralResult<Boolean> updateUserInfoDetail(@RequestBody UpdateUserInfoForm form){
       return GeneralResult.success(userService.updateUserInfoDetail(form));
    }

    @PostMapping("/updatePassword.jsonHtml")
    @ApiOperation(value = "更新用户密码",notes = "更新用户信息")
    GeneralResult<Boolean> updatePassword(@RequestBody NewPasswordForm form){
        return userService.updatePassword(form);
    }
}
