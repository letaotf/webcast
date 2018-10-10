package com.taofeng.webcast.dao.mapper.ext;

import com.taofeng.webcast.dao.domain.NetworkAnchorDO;
import com.taofeng.webcast.dao.query.NetworkAnchorQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NetworkAnchorExtMapper {

    /**
     * 根据条件查询
     * @param query
     * @return
     */
    List<NetworkAnchorDO> selectByQuery(NetworkAnchorQuery query);

    /**
     * 查询出来的数量
     * @param query
     * @return
     */
    Integer countByQuery(NetworkAnchorQuery query);

    /**
     * 更新语句，条件可以为空
     * @param record
     * @param query
     * @return
     */
    int updateByQuerySelective(@Param("record") NetworkAnchorDO record, @Param("query") NetworkAnchorQuery query);
}