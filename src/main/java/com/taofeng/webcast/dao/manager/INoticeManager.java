package com.taofeng.webcast.dao.manager;

import com.taofeng.webcast.dao.domain.NoticesDO;

/**
 * <p>公告查询语句</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/3/7 上午9:54
 * @since V1.0
 */
public interface INoticeManager {

    /**
     * 根据主键id删除
     * @param noticeId
     * @return
     */
    Integer deleteByPrimaryKey(Long noticeId);

    /**
     * 插入一条数据
     * @param record
     * @return
     */
    Integer insert(NoticesDO record);

    /**
     * 插入一条数据
     * @param record
     * @return
     */
    Integer insertSelective(NoticesDO record);

    /**
     * 根据主键查找
     * @param noticeId
     * @return
     */
    NoticesDO selectByPrimaryKey(Long noticeId);

    /**
     * 根据主键进行更新(存在字段为空)
     * @param record
     * @return
     */
    Integer updateByPrimaryKeySelective(NoticesDO record);

    /**
     * 根据主键进行更新(所有字段不能为空)
     * @param record
     * @return
     */
    Integer updateByPrimaryKey(NoticesDO record);
}
