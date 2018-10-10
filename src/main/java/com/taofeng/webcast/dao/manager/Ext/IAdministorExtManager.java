package com.taofeng.webcast.dao.manager.Ext;

import com.taofeng.webcast.dao.domain.AdministorDO;
import com.taofeng.webcast.dao.query.AdministorQuery;

import java.util.List;

/**
 * <p>公告的查询语句</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/2/25 上午11:04
 * @since V1.0
 */
public interface IAdministorExtManager {

    /**
     * 根据条件进行查询
     * @param query
     * @return
     */
    List<AdministorDO> selectByQuery(AdministorQuery query);

    /**
     * 更新语句（条件可以为空）
     * @param record
     * @param query
     * @return
     */
    Integer updateByQuerySelective(AdministorDO record, AdministorQuery query);
}
