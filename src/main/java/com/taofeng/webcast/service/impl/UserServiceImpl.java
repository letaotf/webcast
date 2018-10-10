package com.taofeng.webcast.service.impl;

import com.google.common.eventbus.EventBus;
import com.taofeng.webcast.bus.event.IntergityValuesEvent;
import com.taofeng.webcast.common.DTO.UserInfoDTO;
import com.taofeng.webcast.common.Enum.RecordStatusEnum;
import com.taofeng.webcast.common.Enum.SexEnum;
import com.taofeng.webcast.common.Enum.UserTypeEnum;
import com.taofeng.webcast.common.constant.CookieNameConstant;
import com.taofeng.webcast.common.constant.RedisSessionConstant;
import com.taofeng.webcast.common.constant.SessionConstants;
import com.taofeng.webcast.common.entity.GeneralResult;
import com.taofeng.webcast.common.form.NewPasswordForm;
import com.taofeng.webcast.common.form.UpdateUserInfoForm;
import com.taofeng.webcast.common.form.UserForm;
import com.taofeng.webcast.common.util.BeanCopierUtil;
import com.taofeng.webcast.common.util.CreatOrderNoUtil;
import com.taofeng.webcast.common.util.JacWebSessionUtil;
import com.taofeng.webcast.config.session.manager.Session;
import com.taofeng.webcast.config.session.manager.SessionHolder;
import com.taofeng.webcast.dao.domain.AdministorDO;
import com.taofeng.webcast.dao.domain.NetworkAnchorDO;
import com.taofeng.webcast.dao.domain.NetworkBroadcastTypeDetailsDO;
import com.taofeng.webcast.dao.domain.UserDO;
import com.taofeng.webcast.dao.manager.Ext.IAdministorExtManager;
import com.taofeng.webcast.dao.manager.Ext.INetworkAnchorExtManager;
import com.taofeng.webcast.dao.manager.Ext.IUserExtManager;
import com.taofeng.webcast.dao.manager.IAdministorManager;
import com.taofeng.webcast.dao.manager.INetworkAnchorManager;
import com.taofeng.webcast.dao.manager.INetworkBroadcastTypeDetailsManager;
import com.taofeng.webcast.dao.manager.IUserManager;
import com.taofeng.webcast.dao.query.AdministorQuery;
import com.taofeng.webcast.dao.query.NetworkAnchorQuery;
import com.taofeng.webcast.dao.query.UserQuery;
import com.taofeng.webcast.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * <p>用户登录与注册的逻辑跳转</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/2/25 上午11:17
 * @since V1.0
 */
@Service
@Slf4j
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserManager userManager;
    @Autowired
    private IUserExtManager userExtManager;
    @Autowired
    private SessionHolder sessionHolder;
    @Autowired
    private IAdministorExtManager administorExtManager;
    @Autowired
    private IAdministorManager administorManager;
    @Autowired
    private INetworkAnchorManager networkAnchorManager;
    @Autowired
    private INetworkAnchorExtManager networkAnchorExtManager;
    @Autowired
    private INetworkBroadcastTypeDetailsManager networkBroadcastTypeDetailsManager;
    @Autowired
    private EventBus eventBus;

    /**
     * 登录的校验
     * @param form
     * @return
     */
    @Override
    public GeneralResult<String> login(UserForm form) {
        if(Objects.isNull(form)){
            return GeneralResult.error("","参数为空");
        }
        if(UserTypeEnum.ADMIN.getCode().equals(form.getUserType())){
            AdministorQuery query = new AdministorQuery();
            query.createCriteria().andAdministorNameEqualTo(form.getUserName())
                    .andPasswordEqualTo(form.getPassword())
                    .andStatusEqualTo(RecordStatusEnum.VALID.getCode());
            List<AdministorDO> administorDOS = administorExtManager.selectByQuery(query);
            if(!CollectionUtils.isEmpty(administorDOS) && administorDOS.size()==1){
                //创建登录session
                Session session = loginWithSession(administorDOS.get(0).getAdministorId(),administorDOS.get(0).getAdministorName(),2);
                if(Objects.isNull(session)){
                    return GeneralResult.error("","登录失败");
                }
                return GeneralResult.success("/layout.htm");
            }
        }else{
            //设置查询条件
            UserQuery query = new UserQuery();
            UserQuery.Criteria criteria = query.createCriteria();
            criteria.andUserNameEqualTo(form.getUserName())
                    .andPasswordEqualTo(form.getPassword())
                    .andUserTypeEqualTo(form.getUserType())
                    .andStatusEqualTo(RecordStatusEnum.VALID.getCode());
            //进行查询
            //根据查询出的数量进行判断，值为1时，则查询正确
            List<UserDO> userDOS = userExtManager.selectByQuery(query);
            if(!CollectionUtils.isEmpty(userDOS) && userDOS.size()==1){
                //创建登录session
                Session session = loginWithSession(userDOS.get(0).getUserId(),userDOS.get(0).getUserName(),userDOS.get(0).getUserType());
                if(Objects.isNull(session)){
                    return GeneralResult.error("","登录失败");
                }
                if(UserTypeEnum.USER.getCode().equals(form.getUserType())){
                    return GeneralResult.success("/userOperator.htm");
                }else if(UserTypeEnum.BROADCAST.getCode().equals(form.getUserType())){
                    return GeneralResult.success("/anchorOperator.htm");
                }
            }
        }
        return GeneralResult.error("","登录失败");
    }

    /**
     * 登录创建session
     * @param userId    用户ID
     * @param name      员工姓名
     * @param userType  人员类型
     * @return  会话session
     */
    private Session loginWithSession(Long userId,String name,Integer userType) {
        try {
            HashMap<String, Object> attrs = new HashMap<>();
            attrs.put(SessionConstants.USER_ID, userId);
            attrs.put(SessionConstants.LOGIN_NAME, name);
            attrs.put(SessionConstants.USER_TYPE, userType);
            //新建session
            Session session = sessionHolder.createSession(null, attrs);
            JacWebSessionUtil.setSessionIntoCookie(session.getSessionId(),CookieNameConstant.WEB_SESSION_ID);
            //让老的session失效
            boolean result = sessionHolder.replaceSession(session.getSessionId(), userId.toString(), RedisSessionConstant.WEB_USER_SESSION);
            if(result == true){
                return session;
            }
        }catch (Exception e){
            log.error("创建session异常:{}",e.getMessage());
        }
        return null;
    }

    /**
     * 注册
     * @param form
     * @return
     */
    @Override
    public GeneralResult<String> register(UserForm form) {
        if(Objects.isNull(form)){
            return GeneralResult.error("","参数为空");
        }
        Long result = null;
        IntergityValuesEvent event = null;
        if(UserTypeEnum.USER.getCode().equals(form.getUserType())){
            result = userRegister(form);
            event = new IntergityValuesEvent(result,100.0);
        }else if(UserTypeEnum.ADMIN.getCode().equals(form.getUserType())){
            result = adminRegister(form);
        }else if(UserTypeEnum.BROADCAST.getCode().equals(form.getUserType())){
            result = anchorRegister(form);
            event = new IntergityValuesEvent(result,100.0);
        }
        if(!result.equals(0)){
            eventBus.post(event);
            return GeneralResult.success("/common.htm");
        }
        return GeneralResult.error("","注册失败");
    }

    /**
     * 用户的注册
     * @param form 用户信息
     */
    private Long userRegister(UserForm form){
        //设置查询条件
        UserDO userDO = BeanCopierUtil.convert(form,UserDO.class);
        userDO.setUserNo(CreatOrderNoUtil.createUserNO(new Date()));
        userManager.insertSelective(userDO);
        return userDO.getUserId();
    }

    /**
     * 管理员的注册
     * @param form 用户信息
     */
    private Long adminRegister(UserForm form){
         AdministorDO administorDO = new AdministorDO();
         administorDO.setAdministorName(form.getUserName());
         administorDO.setAdministorSex(form.getUserSex());
         administorDO.setAdministorTel(form.getUserTel());
         administorManager.insertSelective(administorDO);
         return administorDO.getAdministorId();
    }

    /**
     * 主播的注册
     * @param form 用户信息
     */
    @Transactional
    public Long anchorRegister(UserForm form){
         //用户信息的新增
         Long userId = userRegister(form);
         //主播信息的新增
         NetworkBroadcastTypeDetailsDO networkBroadcastTypeDetailsDO = BeanCopierUtil.convert(form,NetworkBroadcastTypeDetailsDO.class);
         networkBroadcastTypeDetailsDO.setNetworkTypeNo(CreatOrderNoUtil.createNetworkBroadCastNO(new Date()));
         networkBroadcastTypeDetailsManager.insertSelective(networkBroadcastTypeDetailsDO);
         Long networkBroadcastId = networkBroadcastTypeDetailsDO.getNetworkBroadcastTypeDetailId();
         //主播信息的新增
         NetworkAnchorDO networkAnchorDO = BeanCopierUtil.convert(form,NetworkAnchorDO.class);
         networkAnchorDO.setUserId(userId);
         networkAnchorDO.setNetworkBroadcastTypeDetailId(networkBroadcastId);
         networkAnchorManager.insertSelective(networkAnchorDO);
         return userId;

    }

    /**
     * 退出登录
     */
    @Override
    public GeneralResult<Boolean> logout() {
        //获取cookie中的sessionId
        String sessionId = JacWebSessionUtil.getSessionIdFromCookie(CookieNameConstant.WEB_SESSION_ID);
        //从redis中获取session
        Session session = sessionHolder.getSessionFromRedis(sessionId);
        if(!Objects.isNull(session)){
            //删除session
            sessionHolder.removeSessionFromRedis(sessionId);
        }
        return GeneralResult.success(Boolean.TRUE);
    }

    /**
     * 修改登录密码
     * @return 成功与否
     */
    @Override
    public GeneralResult<Boolean> updatePassword(NewPasswordForm form) {
        UserQuery query = new UserQuery();
        query.createCriteria().andStatusEqualTo(RecordStatusEnum.VALID.getCode())
                .andUserTelEqualTo(form.getTel());
        UserDO record = new UserDO();
        record.setPassword(form.getPassword());
        Integer result = userExtManager.updateByQuerySelective(record,query);
        if(result >= 1){
           return GeneralResult.success(Boolean.TRUE);
        }
        return GeneralResult.create("","更新失败");
    }

    /**
     * 查询用户信息
     * @return 用户信息map
     */
    @Override
    public GeneralResult<Map<String, Object>> queryLoginUser() {
        //获取cookie中的sessionId
        String sessionId = JacWebSessionUtil.getSessionIdFromCookie(CookieNameConstant.WEB_SESSION_ID);
        //从redis中获取session
        Session session = sessionHolder.getSessionFromRedis(sessionId);
        Map<String,Object> map = new HashMap<>();
        if(Objects.isNull(session)){
            return GeneralResult.error("","session失效");
        }
        map.put("SESSION_ID", session.getSessionId());
        map.put(SessionConstants.USER_ID,  session.getAttribute(SessionConstants.USER_ID));
        map.put(SessionConstants.LOGIN_NAME, session.getAttribute(SessionConstants.LOGIN_NAME));
        map.put(SessionConstants.USER_TYPE, session.getAttribute(SessionConstants.USER_TYPE));
        return GeneralResult.success(map);
    }

    /**
     * 根据用户ids获取用户信息
     * @param userIds 用户IDs
     * @return 用户信息List
     */
    @Override
    public List<UserDO> queryUserInfoBy(List<Long> userIds) {
        if(CollectionUtils.isEmpty(userIds)){
            return Collections.emptyList();
        }
        UserQuery query = new UserQuery();
        query.createCriteria().andUserIdIn(userIds)
                .andStatusEqualTo(RecordStatusEnum.VALID.getCode());
        return userExtManager.selectByQuery(query);
    }

    /**
     * 根据用户id和用户角色 查询用户信息详情
     * @param userId
     * @param userType
     * @return
     */
    @Override
    public UserInfoDTO queryUserInfoDetail(Long userId, Integer userType) {

        List<UserDO> userDOS =  queryUserInfoBy(Collections.singletonList(userId));
        if(CollectionUtils.isEmpty(userDOS)){
            return new UserInfoDTO();
        }
        UserInfoDTO userInfoDTO = BeanCopierUtil.convert(userDOS.get(0),UserInfoDTO.class);
        //设置性别
        userInfoDTO.setSexDesc(SexEnum.getMegByCode(userDOS.get(0).getUserSex()));
        //根据角色的不同进行划分
        if(UserTypeEnum.BROADCAST.getCode().equals(userType)){
            NetworkAnchorQuery query = new NetworkAnchorQuery();
            query.createCriteria().andStatusEqualTo(RecordStatusEnum.VALID.getCode())
                    .andUserIdEqualTo(userId);
            List<NetworkAnchorDO> networkAnchorDOS = networkAnchorExtManager.selectByQuery(query);
            if(CollectionUtils.isEmpty(networkAnchorDOS)){
                return new UserInfoDTO();
            }
            userInfoDTO.setIdentityCard(networkAnchorDOS.get(0).getIdentityCard());
            userInfoDTO.setCardImgPath(networkAnchorDOS.get(0).getCardImgPath());
            userInfoDTO.setNetworkBroadcastTypeDetailId(networkAnchorDOS.get(0).getNetworkBroadcastTypeDetailId());
            String name = networkBroadcastTypeDetailsManager.selectByPrimaryKey(networkAnchorDOS.get(0).getNetworkBroadcastTypeDetailId()).getTypeDetailName();
            userInfoDTO.setNetworkBroadcastTypeName(name);
        }
        return userInfoDTO;
    }

    /**
     * 更新用户信息
     * @param form 需要更新的信息
     */
    @Override
    public Boolean updateUserInfoDetail(UpdateUserInfoForm form) {

        UserDO userDO = BeanCopierUtil.convert(form,UserDO.class);
        userManager.updateByPrimaryKeySelective(userDO);
        //管理员信息
        if(UserTypeEnum.BROADCAST.equals(form.getUserType())){
            NetworkAnchorQuery query = new NetworkAnchorQuery();
            query.createCriteria().andStatusEqualTo(RecordStatusEnum.VALID.getCode())
                    .andUserIdEqualTo(form.getUserId());
            NetworkAnchorDO networkAnchorDO = new NetworkAnchorDO();
            networkAnchorDO.setNetworkBroadcastTypeDetailId(form.getNetworkBroadcastTypeDetailId());
            networkAnchorExtManager.updateByQuerySelective(networkAnchorDO,query);
        }
        return Boolean.TRUE;
    }

}


