package com.taofeng.webcast.dao.manager.impl;

import com.taofeng.webcast.dao.domain.NoticesDO;
import com.taofeng.webcast.dao.manager.INoticeManager;
import com.taofeng.webcast.dao.mapper.NoticesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>公告查询语句</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/3/7 上午9:54
 * @since V1.0
 */
@Component
public class NoticeManagerImpl implements INoticeManager{

    @Autowired
    private NoticesMapper noticesMapper;

    /**
     * 根据主键id删除
     * @param noticeId
     * @return
     */
    @Override
    public Integer deleteByPrimaryKey(Long noticeId) {
        return noticesMapper.deleteByPrimaryKey(noticeId);
    }

    /**
     * 插入一条数据
     * @param record
     * @return
     */
    @Override
    public Integer insert(NoticesDO record) {
        return noticesMapper.insert(record);
    }

    /**
     * 插入一条数据
     * @param record
     * @return
     */
    @Override
    public Integer insertSelective(NoticesDO record) {
        return noticesMapper.insertSelective(record);
    }

    /**
     * 根据主键查找
     * @param noticeId
     * @return
     */
    @Override
    public NoticesDO selectByPrimaryKey(Long noticeId) {
        return noticesMapper.selectByPrimaryKey(noticeId);
    }

    /**
     * 根据主键进行更新(存在字段为空)
     * @param record
     * @return
     */
    @Override
    public Integer updateByPrimaryKeySelective(NoticesDO record) {
        return noticesMapper.updateByPrimaryKeySelective(record);
    }

    /**
     * 根据主键进行更新(所有字段不能为空)
     * @param record
     * @return
     */
    @Override
    public Integer updateByPrimaryKey(NoticesDO record) {
        return noticesMapper.updateByPrimaryKey(record);
    }
}
