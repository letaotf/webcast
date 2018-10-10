package com.taofeng.webcast.dao.manager.impl.Ext;

import com.taofeng.webcast.dao.domain.GoodsDO;
import com.taofeng.webcast.dao.manager.Ext.IGoodsExtManager;
import com.taofeng.webcast.dao.mapper.ext.GoodsExtMapper;
import com.taofeng.webcast.dao.query.GoodsQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>商品表sql语句</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/3/7 上午9:54
 * @since V1.0
 */
@Component
public class GoodsExtManagerImpl implements IGoodsExtManager {

    @Autowired
    private GoodsExtMapper goodsExtMapper;

    /**
     * 根据条件进行查询
     * @param query
     * @return
     */
    @Override
    public List<GoodsDO> selectByQuery(GoodsQuery query) {
        return goodsExtMapper.selectByQuery(query);
    }

    /**
     * 查询出来的数量
     * @param query
     * @return
     */
    @Override
    public Integer countByQuery(GoodsQuery query) {
        return goodsExtMapper.countByQuery(query);
    }

    /**
     * 更新语句（条件可以为空）
     * @param record
     * @param query
     * @return
     */
    @Override
    public Integer updateByQuerySelective(GoodsDO record, GoodsQuery query) {
        return goodsExtMapper.updateByQuerySelective(record,query);
    }
}
