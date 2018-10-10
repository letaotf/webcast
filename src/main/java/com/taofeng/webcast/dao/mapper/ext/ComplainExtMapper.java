package com.taofeng.webcast.dao.mapper.ext;

import com.taofeng.webcast.dao.domain.ComplainDO;
import com.taofeng.webcast.dao.query.ComplainQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ComplainExtMapper {

    /**
     * 根据条件查询
     * @param query
     * @return
     */
    List<ComplainDO> selectByQuery(ComplainQuery query);

    /**
     * 查询出来的数量
     * @param query
     * @return
     */
    Integer countByQuery(ComplainQuery query);

    /**
     * 更新语句，条件可以为空
     * @param record
     * @param query
     * @return
     */
    int updateByQuerySelective(@Param("record") ComplainDO record, @Param("query") ComplainQuery query);
}