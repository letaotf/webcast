package com.taofeng.webcast.dao.manager.Ext;

import com.taofeng.webcast.dao.domain.DealComplainDO;
import com.taofeng.webcast.dao.query.DealComplainQuery;

import java.util.List;

/**
 * <p>投诉表sql语句</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/3/7 上午9:54
 * @since V1.0
 */
public interface IDealComplainExtManager {

    /**
     * 根据条件进行查询
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
    Integer updateByQuerySelective(DealComplainDO record, DealComplainQuery query);

}
