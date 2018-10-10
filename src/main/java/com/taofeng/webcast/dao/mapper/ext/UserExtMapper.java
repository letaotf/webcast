package com.taofeng.webcast.dao.mapper.ext;

import com.taofeng.webcast.dao.domain.UserDO;
import com.taofeng.webcast.dao.query.UserQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserExtMapper {

    /**
     * 根据条件查询
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
     * @param key1
     * @param key2
     * @return
     */
    List<UserDO> selectUserByKeyLike(@Param("key1") String key1,@Param("key2") String key2);

    /**
     * 根据用户名模糊查询出用户ID
     * @param userName
     * @return
     */
    Long selectUserIdByName(@Param("userName") String userName);

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
    List<UserDO> selectUserIdByUserType(@Param("userType") Integer userType);

    /**
     * 更新语句，条件可以为空
     * @param record
     * @param query
     * @return
     */
    int updateByQuerySelective(@Param("record") UserDO record, @Param("query") UserQuery query);
}