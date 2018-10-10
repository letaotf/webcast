package com.taofeng.webcast.dao.mapper;

import com.taofeng.webcast.dao.domain.NoticesDO;

public interface NoticesMapper {
    int deleteByPrimaryKey(Long noticeId);

    int insert(NoticesDO record);

    int insertSelective(NoticesDO record);

    NoticesDO selectByPrimaryKey(Long noticeId);

    int updateByPrimaryKeySelective(NoticesDO record);

    int updateByPrimaryKey(NoticesDO record);
}