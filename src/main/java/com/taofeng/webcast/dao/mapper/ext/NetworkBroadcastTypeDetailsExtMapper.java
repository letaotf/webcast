package com.taofeng.webcast.dao.mapper.ext;

import com.taofeng.webcast.dao.domain.NetworkBroadcastTypeDetailsDO;
import com.taofeng.webcast.dao.query.NetworkBroadcastTypeDetailsQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NetworkBroadcastTypeDetailsExtMapper {
    /**
     * 根据条件查询
     * @param query
     * @return
     */
    List<NetworkBroadcastTypeDetailsDO> selectByQuery(NetworkBroadcastTypeDetailsQuery query);

    /**
     * 查询出来的数量
     * @param query
     * @return
     */
    Integer countByQuery(NetworkBroadcastTypeDetailsQuery query);

    /**
     * 更新语句，条件可以为空
     * @param record
     * @param query
     * @return
     */
    int updateByQuerySelective(@Param("record") NetworkBroadcastTypeDetailsDO record, @Param("query") NetworkBroadcastTypeDetailsQuery query);
}