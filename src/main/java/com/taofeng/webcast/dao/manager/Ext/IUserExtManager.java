package com.taofeng.webcast.dao.manager.Ext;

import com.taofeng.webcast.dao.domain.UserDO;
import com.taofeng.webcast.dao.query.UserQuery;

import java.util.List;

/**
 * <p>web_user的查询语句</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/2/25 上午11:04
 * @since V1.0
 */
public interface IUserExtManager {

    /**
     * 根据=条件进行查询
     * @param query
     * @return
     */
    List<UserDO> selectByQuery(UserQuery query);

    /**
     * 查询出来的数量
     * @param query
     * @return
     */
    Integer countByQuery(UserQuery query);

    /**
     * 根据userName和userNO进行模糊查询(两个参数存在为空的情况)
     * @param userName
     * @param
     * @return
     */
    List<UserDO> selectUserByKeyLike(String userName,String userNO);

    /**
     * 根据用户名模糊查询出用户ID
     * @param userName
     * @return
     */
    Long selectUserIdByName(String userName);

    /**
     * 分页查询出userId
     * @param query
     * @return
     */
    List<Long> selectUserIdByKeyLike(UserQuery query);

    /**
     * 根据用户类型进行查询
     * @param userType
     * @return
     */
    List<UserDO> selectUserIdByUserType(Integer userType);

    /**
     * 更新语句，条件可以为空
     * @param record
     * @param query
     * @return
     */
    Integer updateByQuerySelective(UserDO record, UserQuery query);
}
