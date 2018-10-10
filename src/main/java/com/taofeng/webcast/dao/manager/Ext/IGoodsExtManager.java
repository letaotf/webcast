package com.taofeng.webcast.dao.manager.Ext;

import com.taofeng.webcast.dao.domain.GoodsDO;
import com.taofeng.webcast.dao.query.GoodsQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>商品查询语句</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/3/7 上午9:54
 * @since V1.0
 */
public interface IGoodsExtManager {

    /**
     * 根据条件进行查询
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
    Integer updateByQuerySelective(@Param("record") GoodsDO record, @Param("query") GoodsQuery query);
}
