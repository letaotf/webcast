package com.taofeng.webcast.dao.mapper.ext;

import com.taofeng.webcast.dao.domain.GoodsDO;
import com.taofeng.webcast.dao.query.GoodsQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodsExtMapper {

    /**
     * 根据条件查询
     * @param query
     * @return
     */
    List<GoodsDO> selectByQuery(GoodsQuery query);

    /**
     * 查询出来的数量
     * @param query
     * @return
     */
    Integer countByQuery(GoodsQuery query);

    /**
     * 更新语句（条件可以为空）
     * @param record
     * @param query
     * @return
     */
    int updateByQuerySelective(@Param("record") GoodsDO record,@Param("query") GoodsQuery query);
}