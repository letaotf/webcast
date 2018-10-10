package com.taofeng.webcast.dao.manager.impl.Ext;

import com.taofeng.webcast.dao.domain.BuyGoodsDO;
import com.taofeng.webcast.dao.manager.Ext.IBuyGoodsExtManager;
import com.taofeng.webcast.dao.mapper.ext.BuyGoodsExtMapper;
import com.taofeng.webcast.dao.query.BuyGoodsQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>购买客户购买商品管理</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/5/11 下午3:56
 * @since V1.0
 */
@Component
public class BuyGoodsExtManagerImpl implements IBuyGoodsExtManager{

    @Autowired
    private BuyGoodsExtMapper buyGoodsExtMapper;

    @Override
    public List<BuyGoodsDO> selectByQuery(BuyGoodsQuery query) {
        return buyGoodsExtMapper.selectByQuery(query);
    }

    @Override
    public Integer countByQuery(BuyGoodsQuery query) {
        return buyGoodsExtMapper.countByQuery(query);
    }

    @Override
    public Integer updateByQuerySelective(BuyGoodsDO record, BuyGoodsQuery query) {
        return buyGoodsExtMapper.updateByQuerySelective(record,query);
    }
}
