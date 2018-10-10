package com.taofeng.webcast.dao.manager.Ext;

import com.taofeng.webcast.dao.domain.NoticesDO;
import com.taofeng.webcast.dao.query.NoticeQuery;

import java.util.List;

/**
 * <p>公告的查询语句</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/2/25 上午11:04
 * @since V1.0
 */
public interface INoticesExtManager {

    /**
     * 根据条件进行查询
     * @param query
     * @return
     */
    List<NoticesDO> selectByQuery(NoticeQuery query);

    /**
     * 查询出来的数量
     * @param query
     * @return
     */
    Integer countByQuery(NoticeQuery query);
}
