package com.taofeng.webcast.dao.manager.Ext;

import com.taofeng.webcast.dao.domain.BuyGoodsDO;
import com.taofeng.webcast.dao.query.BuyGoodsQuery;

import java.util.List;

/**
 * <p>描述</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/5/11 下午3:56
 * @since V1.0
 */
public interface IBuyGoodsExtManager {
    /**
     * 根据条件进行查询
     * @param query
     * @return
     */
    List<BuyGoodsDO> selectByQuery(BuyGoodsQuery query);

    /**
     * 查询出来的数量
     * @param query
     * @return
     */
    Integer countByQuery(BuyGoodsQuery query);

    /**
     * 更新语句（条件可以为空）
     * @param record
     * @param query
     * @return
     */
    Integer updateByQuerySelective(BuyGoodsDO record,BuyGoodsQuery query);
}
