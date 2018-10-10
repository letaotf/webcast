package com.taofeng.webcast.dao.manager.impl.Ext;

import com.taofeng.webcast.dao.domain.AdministorDO;
import com.taofeng.webcast.dao.manager.Ext.IAdministorExtManager;
import com.taofeng.webcast.dao.mapper.ext.AdministorExtMapper;
import com.taofeng.webcast.dao.query.AdministorQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>公告查询语句</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/3/7 上午9:54
 * @since V1.0
 */
@Component
public class AdministorExtManagerImpl implements IAdministorExtManager {

    @Autowired
    private AdministorExtMapper administorExtMapper;

    /**
     * 根据条件进行查询
     * @param query
     * @return
     */
    @Override
    public List<AdministorDO> selectByQuery(AdministorQuery query) {
        return administorExtMapper.selectByQuery(query);
    }

    /**
     * 更新语句（条件可以为空）
     * @param record
     * @param query
     * @return
     */
    @Override
    public Integer updateByQuerySelective(AdministorDO record, AdministorQuery query) {
        return administorExtMapper.updateByQuerySelective(record,query);
    }


}
