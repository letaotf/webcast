package com.taofeng.webcast.dao.mapper.ext;

import com.taofeng.webcast.dao.domain.DealComplainDO;
import com.taofeng.webcast.dao.query.DealComplainQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DealComplainExtMapper {

    /**
     * 根据条件查询
     * @param query
     * @return
     */
    List<DealComplainDO> selectByQuery(DealComplainQuery query);

    /**
     * 查询出来的数量
     * @param query
     * @return
     */
    Integer countByQuery(DealComplainQuery query);

    /**
     * 更新语句，条件可以为空
     * @param record
     * @param query
     * @return
     */
    int updateByQuerySelective(@Param("record") DealComplainDO record, @Param("query") DealComplainQuery query);
}