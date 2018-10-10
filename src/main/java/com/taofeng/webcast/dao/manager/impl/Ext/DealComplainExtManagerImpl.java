package com.taofeng.webcast.dao.manager.impl.Ext;

import com.taofeng.webcast.dao.domain.DealComplainDO;
import com.taofeng.webcast.dao.manager.Ext.IDealComplainExtManager;
import com.taofeng.webcast.dao.mapper.ext.DealComplainExtMapper;
import com.taofeng.webcast.dao.query.DealComplainQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>投诉表sql语句</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/3/7 上午9:54
 * @since V1.0
 */
@Component
public class DealComplainExtManagerImpl implements IDealComplainExtManager {

    @Autowired
    private DealComplainExtMapper dealComplainExtMapper;


    /**
     * 根据条件进行查询
     * @param query
     * @return
     */
    @Override
    public List<DealComplainDO> selectByQuery(DealComplainQuery query) {
        return dealComplainExtMapper.selectByQuery(query);
    }

    /**
     * 查询出来的数量
     * @param query
     * @return
     */
    @Override
    public Integer countByQuery(DealComplainQuery query) {
        return dealComplainExtMapper.countByQuery(query);
    }

    /**
     * 更新语句，条件可以为空
     * @param record
     * @param query
     * @return
     */
    @Override
    public Integer updateByQuerySelective(DealComplainDO record, DealComplainQuery query) {
        return dealComplainExtMapper.updateByQuerySelective(record,query);
    }
}
