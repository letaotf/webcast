package com.taofeng.webcast.dao.manager.impl.Ext;

import com.taofeng.webcast.dao.domain.UserDO;
import com.taofeng.webcast.dao.manager.Ext.IUserExtManager;
import com.taofeng.webcast.dao.mapper.ext.UserExtMapper;
import com.taofeng.webcast.dao.query.UserQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>web_user表的数据库操作语句,给service提供接口</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/2/25 上午11:05
 * @since V1.0
 */
@Component
public class UserExtManagerImpl implements IUserExtManager{

    @Autowired
    private UserExtMapper userExtMapper;

    /**
     * 根据条件进行查询
     * @param query
     * @return
     */
    @Override
    public List<UserDO> selectByQuery(UserQuery query) {
        return userExtMapper.selectByQuery(query);
    }

    /**
     * 查询出来的数量
     * @param query
     * @return
     */
    @Override
    public Integer countByQuery(UserQuery query) {
        return userExtMapper.countByQuery(query);
    }

    /**
     * 根据userName和userNO进行模糊查询(两个参数存在为空的情况)
     * @param userName
     * @param
     * @return
     */
    @Override
    public List<UserDO> selectUserByKeyLike(String userName, String userNO) {
        return userExtMapper.selectUserByKeyLike(userName,userNO);
    }

    /**
     * 根据用户名模糊查询出用户ID
     * @param userName
     * @return
     */
    @Override
    public Long selectUserIdByName(String userName) {
        return userExtMapper.selectUserIdByName(userName);
    }

    /**
     * 分页查询出userId
     * @param query
     * @return
     */
    @Override
    public List<Long> selectUserIdByKeyLike(UserQuery query) {
        return userExtMapper.selectUserIdByKeyLike(query);
    }

    /**
     * 根据用户类型进行查询
     * @param userType
     * @return
     */
    @Override
    public List<UserDO> selectUserIdByUserType(Integer userType) {
        return userExtMapper.selectUserIdByUserType(userType);
    }

    /**
     * 更新语句，条件可以为空
     * @param record
     * @param query
     * @return
     */
    @Override
    public Integer updateByQuerySelective(UserDO record, UserQuery query) {
        return userExtMapper.updateByQuerySelective(record,query);
    }
}
