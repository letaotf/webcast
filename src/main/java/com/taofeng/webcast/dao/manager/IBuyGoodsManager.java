package com.taofeng.webcast.dao.manager;

import com.taofeng.webcast.dao.domain.BuyGoodsDO;

/**
 * <p>描述</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/5/10 下午4:26
 * @since V1.0
 */
public interface IBuyGoodsManager {

    /**
     * 根据主键id删除
     * @param buyGoodsId
     * @return
     */
    Integer deleteByPrimaryKey(Long buyGoodsId);

    /**
     * 插入一条数据
     * @param record
     * @return
     */
    Integer insert(BuyGoodsDO record);

    /**
     * 插入一条数据
     * @param record
     * @return
     */
    Integer insertSelective(BuyGoodsDO record);

    /**
     * 根据主键查找
     * @param buyGoodsId
     * @return
     */
    BuyGoodsDO selectByPrimaryKey(Long buyGoodsId);

    /**
     * 根据主键进行更新
     * @param record
     * @return
     */
    Integer updateByPrimaryKeySelective(BuyGoodsDO record);

    /**
     * 根据主键进行更新
     * @param record
     * @return
     */
    Integer updateByPrimaryKey(BuyGoodsDO record);

}
