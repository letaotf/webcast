package com.taofeng.webcast.service;

import com.taofeng.webcast.common.DTO.UserInfoDTO;
import com.taofeng.webcast.common.entity.GeneralResult;
import com.taofeng.webcast.common.form.NewPasswordForm;
import com.taofeng.webcast.common.form.UpdateUserInfoForm;
import com.taofeng.webcast.common.form.UserForm;
import com.taofeng.webcast.dao.domain.UserDO;

import java.util.List;
import java.util.Map;

/**
 * <p>用户的登入与注册</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/2/25 上午11:17
 * @since V1.0
 */
public interface IUserService {

    /**
     * 登录的校验
     * @param form
     * @return
     */
    GeneralResult<String> login(UserForm form);

    /**
     * 注册
     * @param form
     * @return
     */
    GeneralResult<String> register(UserForm form);

    /**
     * 退出登录
     */
    GeneralResult<Boolean> logout();

    /**
     * 修改登录密码
     * @Param  form
     * @return 成功与否
     */
    GeneralResult<Boolean> updatePassword(NewPasswordForm form);

    /**
     * 查询用户信息
     * @return 用户信息map
     */
    GeneralResult<Map<String,Object>> queryLoginUser();

    /**
     * 根据用户ids获取用户信息
     * @param userIds 用户IDs
     * @return 用户信息List
     */
    List<UserDO> queryUserInfoBy(List<Long> userIds);

    /**
     * 根据用户id和用户角色 查询用户信息详情
     * @param userId
     * @param userType
     * @return
     */
    UserInfoDTO queryUserInfoDetail(Long userId,Integer userType);

    /**
     * 更新用户信息
     * @param form 需要更新的信息
     */
    Boolean updateUserInfoDetail(UpdateUserInfoForm form);
}
