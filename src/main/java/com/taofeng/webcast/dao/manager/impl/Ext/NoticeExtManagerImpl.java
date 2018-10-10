package com.taofeng.webcast.dao.manager.impl.Ext;

import com.taofeng.webcast.dao.domain.NoticesDO;
import com.taofeng.webcast.dao.manager.Ext.INoticesExtManager;
import com.taofeng.webcast.dao.mapper.ext.NoticesExtMapper;
import com.taofeng.webcast.dao.query.NoticeQuery;
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
public class NoticeExtManagerImpl implements INoticesExtManager {

    @Autowired
    private NoticesExtMapper noticesExtMapper;

    /**
     * 根据条件进行查询
     * @param query
     * @return
     */
    @Override
    public List<NoticesDO> selectByQuery(NoticeQuery query) {
        return noticesExtMapper.selectByQuery(query);
    }

    /**
     * 查询出来的数量
     * @param query
     * @return
     */
    @Override
    public Integer countByQuery(NoticeQuery query) {
        return noticesExtMapper.countByQuery(query);
    }
}
