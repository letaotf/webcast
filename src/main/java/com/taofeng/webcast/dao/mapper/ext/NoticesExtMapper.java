package com.taofeng.webcast.dao.mapper.ext;

import com.taofeng.webcast.dao.domain.NoticesDO;
import com.taofeng.webcast.dao.query.NoticeQuery;

import java.util.List;

public interface NoticesExtMapper {
    /**
     * 根据条件查询
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