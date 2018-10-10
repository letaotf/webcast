package com.taofeng.webcast.dao.manager;

import com.taofeng.webcast.dao.domain.UserDO;

/**
 * <p>web_user的查询语句</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/2/25 上午11:04
 * @since V1.0
 */
public interface IUserManager {
    /**
     * 根据主键id删除
     * @param uesrId
     * @return
     */
    Integer deleteByPrimaryKey(Long uesrId);

    /**
     * 插入一条数据
     * @param record
     * @return
     */
    Integer insert(UserDO record);

    /**
     * 插入一条数据
     * @param record
     * @return
     */
    Long insertSelective(UserDO record);

    /**
     * 根据主键查找
     * @param uesrId
     * @return
     */
    UserDO selectByPrimaryKey(Long uesrId);

    /**
     * 根据主键进行更新
     * @param record
     * @return
     */
    Integer updateByPrimaryKeySelective(UserDO record);

    /**
     * 根据主键进行更新
     * @param record
     * @return
     */
    Integer updateByPrimaryKey(UserDO record);

}
