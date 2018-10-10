package com.taofeng.webcast.dao.mapper.ext;

import com.taofeng.webcast.dao.domain.AdministorDO;
import com.taofeng.webcast.dao.query.AdministorQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdministorExtMapper {
    /**
     * 根据条件查询
     * @param query
     * @return
     */
    List<AdministorDO> selectByQuery(AdministorQuery query);

    /**
     * 更新语句，条件可以为空
     * @param record
     * @param query
     * @return
     */
    int updateByQuerySelective(@Param("record") AdministorDO record, @Param("query") AdministorQuery query);

}