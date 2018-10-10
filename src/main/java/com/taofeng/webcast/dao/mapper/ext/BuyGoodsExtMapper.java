package com.taofeng.webcast.dao.mapper.ext;

import com.taofeng.webcast.dao.domain.BuyGoodsDO;
import com.taofeng.webcast.dao.query.BuyGoodsQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>购买商品</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/5/10 下午4:30
 * @since V1.0
 */
public interface BuyGoodsExtMapper {

    /**
     * 根据条件查询
     * @param query
     * @return
     */
    List<BuyGoodsDO> selectByQuery(BuyGoodsQuery query);

    /**
     * 查询出来的数量
     * @param query
     * @return
     */
    Integer countByQuery(BuyGoodsQuery query);

    /**
     * 更新语句，条件可以为空
     * @param record
     * @param query
     * @return
     */
    int updateByQuerySelective(@Param("record") BuyGoodsDO record, @Param("query") BuyGoodsQuery query);
}
