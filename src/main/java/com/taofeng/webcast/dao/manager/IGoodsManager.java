package com.taofeng.webcast.dao.manager;

import com.taofeng.webcast.dao.domain.GoodsDO;

/**
 * <p>商品查询语句</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/3/7 上午9:54
 * @since V1.0
 */
public interface IGoodsManager {

    /**
     * 根据主键id删除
     * @param goodsId
     * @return
     */
    Integer deleteByPrimaryKey(Long goodsId);

    /**
     * 插入一条数据
     * @param record
     * @return
     */
    Integer insert(GoodsDO record);

    /**
     * 插入一条数据
     * @param record
     * @return
     */
    Integer insertSelective(GoodsDO record);

    /**
     * 根据主键查找
     * @param goodsId
     * @return
     */
    GoodsDO selectByPrimaryKey(Long goodsId);

    /**
     * 根据主键进行更新(存在字段为空)
     * @param record
     * @return
     */
    Integer updateByPrimaryKeySelective(GoodsDO record);

    /**
     * 根据主键进行更新(所有字段不能为空)
     * @param record
     * @return
     */
    Integer updateByPrimaryKey(GoodsDO record);
}
