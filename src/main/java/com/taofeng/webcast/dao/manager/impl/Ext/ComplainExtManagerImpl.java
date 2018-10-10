package com.taofeng.webcast.dao.manager.impl.Ext;

import com.taofeng.webcast.dao.domain.ComplainDO;
import com.taofeng.webcast.dao.manager.Ext.IComplainExtManager;
import com.taofeng.webcast.dao.mapper.ext.ComplainExtMapper;
import com.taofeng.webcast.dao.query.ComplainQuery;
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
public class ComplainExtManagerImpl implements IComplainExtManager {

    @Autowired
    private ComplainExtMapper complainExtMapper;

    /**
     * 根据条件进行查询
     * @param query
     * @return
     */
    @Override
    public List<ComplainDO> selectByQuery(ComplainQuery query) {
        return complainExtMapper.selectByQuery(query);
    }

    /**
     * 查询出来的数量
     * @param query
     * @return
     */
    @Override
    public Integer countByQuery(ComplainQuery query) {
        return complainExtMapper.countByQuery(query);
    }

    /**
     * 更新语句，条件可以为空
     * @param record
     * @param query
     * @return
     */
    @Override
    public Integer updateByQuerySelective(ComplainDO record, ComplainQuery query) {
        return complainExtMapper.updateByQuerySelective(record,query);
    }
}
