package com.taofeng.webcast.service;

import com.taofeng.webcast.common.DTO.BuyGoodsRecordDTO;
import com.taofeng.webcast.common.DTO.GoodsDTO;
import com.taofeng.webcast.common.entity.GeneralResult;
import com.taofeng.webcast.common.entity.PageResult;
import com.taofeng.webcast.common.form.*;
import com.taofeng.webcast.dao.domain.BuyGoodsDO;

import java.util.List;

/**
 * <p>商品管理</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/3/12 下午5:35
 * @since V1.0
 */
public interface IGoodsManagementService{

    /**
     * 查询在架上的商品
     * @param form
     * @return
     */
    GeneralResult<PageResult<GoodsDTO>> queryOnlineListGoods(GoodsForm form);

    /**
     * 查询全部的商品
     * @param form
     * @return
     */
    GeneralResult<PageResult<GoodsDTO>> queryAllListGoods(GoodsForm form);

    /**
     * 添加商品
     * @param form
     * @retur
     */
    GeneralResult<Boolean> addGoods(AddGoodForm form);

    /**
     * 更新商品
     * @param form
     * @return
     */
    GeneralResult<Boolean> updateGoods(UpdateGoodsForm form);

    /**
     * 删除商品
     * @param form
     * @return
     */
    GeneralResult<Boolean> deleteGoods(DeleteGoodsForm form);

    /**
     * 处理商品的在线状态
     * @param form
     * @return
     */
    GeneralResult<Boolean> dealGoodsOnlineStatus(GoodsOnlineForm form);

    /**
     * 查询用户的购买记录
     * @param userId 用户ID
     * @return 购买记录
     */
    GeneralResult<List<BuyGoodsRecordDTO>> queryBuyGoodsRecord(Long userId);

    /**
     * 根据用户id 查询用户的购买记录详情
     * @param userId
     * @return
     */
    List<BuyGoodsDO> queryBuyGoodsList(Long userId);

    /**
     * 增加购买商品
     * @param form 商品信息
     * @return
     */
    GeneralResult<Boolean> addBuyGoods(BuyGoodsForm form);
}
