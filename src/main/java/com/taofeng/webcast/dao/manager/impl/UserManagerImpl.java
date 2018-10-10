package com.taofeng.webcast.dao.manager.impl;

import com.taofeng.webcast.dao.domain.UserDO;
import com.taofeng.webcast.dao.manager.IUserManager;
import com.taofeng.webcast.dao.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>web_user表的数据库操作语句,给service提供接口</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/2/25 上午11:05
 * @since V1.0
 */
@Component
public class UserManagerImpl implements IUserManager{

    @Autowired
    private UserMapper userMapper;

    @Override
    public Integer deleteByPrimaryKey(Long uesrId) {
        return userMapper.deleteByPrimaryKey(uesrId);
    }

    @Override
    public Integer insert(UserDO record) {
        return userMapper.insert(record);
    }

    @Override
    public Long insertSelective(UserDO record) {
        return userMapper.insertSelective(record);
    }

    @Override
    public UserDO selectByPrimaryKey(Long uesrId) {
        return userMapper.selectByPrimaryKey(uesrId);
    }

    @Override
    public Integer updateByPrimaryKeySelective(UserDO record) {
        return userMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public Integer updateByPrimaryKey(UserDO record) {
        return userMapper.updateByPrimaryKey(record);
    }



}
