package com.taofeng.webcast.service.impl;

import com.taofeng.webcast.common.DTO.BuyGoodsRecordDTO;
import com.taofeng.webcast.common.DTO.GoodsDTO;
import com.taofeng.webcast.common.Enum.EmployStatusEnum;
import com.taofeng.webcast.common.Enum.ErrorCodeEnum;
import com.taofeng.webcast.common.Enum.GoodsOnlineStatusEnum;
import com.taofeng.webcast.common.Enum.RecordStatusEnum;
import com.taofeng.webcast.common.entity.GeneralResult;
import com.taofeng.webcast.common.entity.PageResult;
import com.taofeng.webcast.common.form.*;
import com.taofeng.webcast.common.util.BeanCopierUtil;
import com.taofeng.webcast.common.util.CreatOrderNoUtil;
import com.taofeng.webcast.common.util.DateUtil;
import com.taofeng.webcast.dao.domain.BuyGoodsDO;
import com.taofeng.webcast.dao.domain.GoodsDO;
import com.taofeng.webcast.dao.manager.Ext.IBuyGoodsExtManager;
import com.taofeng.webcast.dao.manager.Ext.IGoodsExtManager;
import com.taofeng.webcast.dao.manager.IBuyGoodsManager;
import com.taofeng.webcast.dao.mapper.GoodsMapper;
import com.taofeng.webcast.dao.query.BuyGoodsQuery;
import com.taofeng.webcast.dao.query.GoodsQuery;
import com.taofeng.webcast.service.IGoodsManagementService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * <p>商品管理</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/3/12 下午5:34
 * @since V1.0
 */
@Service
@Slf4j
public class GoodsManagementServiceImpl implements IGoodsManagementService{

    @Autowired
    private IGoodsExtManager goodsExtManager;
    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private IBuyGoodsExtManager buyGoodsExtManager;
    @Autowired
    private IBuyGoodsManager buyGoodsManager;

    /**
     * 查询在架上的商品
     * @param form
     * @return
     */
    @Override
    public GeneralResult<PageResult<GoodsDTO>> queryOnlineListGoods(GoodsForm form) {
        if(Objects.isNull(form)){
            log.error("查询在架上的商品入参条件为空");
            return GeneralResult.error(ErrorCodeEnum.NO_DATA.getErrorMeg(),"查询在架上的商品入参条件为空");
        }
        GoodsQuery query = new GoodsQuery();
        GoodsQuery.Criteria criteria = query.createCriteria();
        query.setPageNo(form.getPageNo());
        query.setPageSize(form.getPageSize());
        buildGoodsQuery(criteria,form);
        criteria.andGoodsOnlineStatusEqualTo(GoodsOnlineStatusEnum.ONLINE.getCode());
        PageResult<GoodsDTO> result = buildGoodsDTO(query,form);
        if(Objects.isNull(result)){
            return GeneralResult.error(ErrorCodeEnum.NO_DATA.getErrorMeg(),"暂无数据");
        }
        return GeneralResult.success(result);
    }

    /**
     * 设置GoodsQuery
     * @param criteria
     */
    private void buildGoodsQuery(GoodsQuery.Criteria criteria,GoodsForm form){

         if(!Objects.isNull(form.getGoodsName())){
             criteria.andGoodsNameLike("%"+form.getGoodsName()+"%");
         }
         if(!Objects.isNull(form.getUnderPrice()) && !Objects.isNull(form.getUpPrice())){
             criteria.andGoodsPriceBetween(form.getUpPrice(),form.getUnderPrice());
         }
         if(!Objects.isNull(form.getUnderPrice()) && Objects.isNull(form.getUpPrice())){
             criteria.andGoodsPriceGreaterThanOrEqualTo(form.getUpPrice());
         }
         if(Objects.isNull(form.getUnderPrice()) && !Objects.isNull(form.getUpPrice())){
            criteria.andGoodsPriceBetween(0D,form.getUnderPrice());
         }
         if(!Objects.isNull(form.getDate()) && !form.getDate().equals("")) {
             criteria.andGmtCreateEqualTo(DateUtil.ofLocalDateTime(DateUtil.convertStringToDateYMD(form.getDate())));
         }
         criteria.andStatusEqualTo(RecordStatusEnum.VALID.getCode());
    }


    /**
     * 查询全部的商品
     * @param form
     * @return
     */
    @Override
    public GeneralResult<PageResult<GoodsDTO>> queryAllListGoods(GoodsForm form) {
        if(Objects.isNull(form)){
            log.error("查询在架上的商品入参条件为空");
            return GeneralResult.error(ErrorCodeEnum.NO_DATA.getErrorMeg(),"查询在架上的商品入参条件为空");
        }
        GoodsQuery query = new GoodsQuery();
        GoodsQuery.Criteria criteria = query.createCriteria();
        buildGoodsQuery(criteria,form);
        PageResult<GoodsDTO> result = buildGoodsDTO(query,form);
        if(Objects.isNull(result)){
            return GeneralResult.error(ErrorCodeEnum.NO_DATA.getErrorMeg(),"暂无数据");
        }
        return GeneralResult.success(result);
    }

    /**
     * 构建GoodsDTO
     * @param query
     * @param form
     * @return
     */
    private PageResult<GoodsDTO> buildGoodsDTO(GoodsQuery query,GoodsForm form){
        List<GoodsDO> goodsDOS = goodsExtManager.selectByQuery(query);
        if(CollectionUtils.isEmpty(goodsDOS)){
            return null;
        }
        PageResult<GoodsDTO> result = new PageResult<>();
        result.setPageNo(form.getPageNo());
        result.setPageSize(form.getPageSize());
        result.setTotalCount(goodsExtManager.countByQuery(query));
        List<GoodsDTO> goodsDTOS = new ArrayList<>();
        goodsDOS.forEach(goodsDO -> {
            GoodsDTO goodsDTO = BeanCopierUtil.convert(goodsDO,GoodsDTO.class);
            goodsDTO.setGmtModified(goodsDO.getGmtModified().toString().replace("T"," "));
            goodsDTOS.add(goodsDTO);
        });
        result.setResult(goodsDTOS);
        return result;
    }


    /**
     * 添加商品
     * @param form
     * @return
     */
    @Override
    public GeneralResult<Boolean> addGoods(AddGoodForm form) {
        if(Objects.isNull(form)){
            return GeneralResult.error(ErrorCodeEnum.NO_DATA.getErrorMeg(),"更新商品时参数为空");
        }
        //创建商品编号
        String goodsNo = CreatOrderNoUtil.createGoodsNO(new Date());
        //更新商品的价格
        GoodsDO goodsDO = new GoodsDO();
        goodsDO.setGoodsName(form.getGoodsName());
        goodsDO.setGoodsPrice(form.getGoodsPrice());
        goodsDO.setGoodsNo(goodsNo);
        goodsDO.setImgPath(form.getImgPath());

        Integer result = goodsMapper.insertSelective(goodsDO);
        if(result>=0){
            return GeneralResult.success(Boolean.TRUE);
        }
        return GeneralResult.create(Boolean.FALSE);
    }

    /**
     * 更新商品
     * @param form
     * @return
     */
    @Override
    public GeneralResult<Boolean> updateGoods(UpdateGoodsForm form) {
        if(Objects.isNull(form)){
            return GeneralResult.error(ErrorCodeEnum.NO_DATA.getErrorMeg(),"更新商品时参数为空");
        }
        GoodsQuery query = new GoodsQuery();
        query.createCriteria()
                .andGoodsIdEqualTo(form.getGoodsId())
                .andStatusEqualTo(RecordStatusEnum.VALID.getCode());
        //更新商品的价格
        GoodsDO goodsDO = new GoodsDO();
        goodsDO.setGoodsName(form.getGoodsName());
        goodsDO.setGoodsPrice(form.getGoodsPrice());
        goodsDO.setImgPath(form.getImgPath());

        Integer result = goodsExtManager.updateByQuerySelective(goodsDO,query);
        if(result>=0){
            return GeneralResult.success(Boolean.TRUE);
        }
        return GeneralResult.create(Boolean.FALSE);
    }

    /**
     * 删除商品
     * @param form
     * @return
     */
    @Override
    public GeneralResult<Boolean> deleteGoods(DeleteGoodsForm form) {
        if(Objects.isNull(form)){
            return GeneralResult.error(ErrorCodeEnum.NO_DATA.getErrorMeg(),"删除商品时主键为空");
        }
        GoodsQuery query = new GoodsQuery();
        query.createCriteria()
                .andGoodsIdEqualTo(form.getGoodsId())
                .andStatusEqualTo(RecordStatusEnum.VALID.getCode());
        //将数据库中的status置为无效不做逻辑删除
        GoodsDO goodsDO = new GoodsDO();
        goodsDO.setStatus(RecordStatusEnum.NO_VALID.getCode());

        Integer result = goodsExtManager.updateByQuerySelective(goodsDO,query);

        if(result>=0){
            return GeneralResult.success(Boolean.TRUE);
        }
        return GeneralResult.create(Boolean.FALSE);
    }

    /**
     * 处理商品的在线状态
     * @param form
     * @return
     */
    @Override
    public GeneralResult<Boolean> dealGoodsOnlineStatus(GoodsOnlineForm form) {
        if(Objects.isNull(form)){
            return GeneralResult.error(ErrorCodeEnum.NO_DATA.getErrorMeg(),"处理商品的在线状态时条件为空");
        }
        GoodsQuery query = new GoodsQuery();
        query.createCriteria()
                .andGoodsIdEqualTo(form.getGoodsId())
                .andStatusEqualTo(RecordStatusEnum.VALID.getCode());
        //对数据库中
        GoodsDO goodsDO = new GoodsDO();
        goodsDO.setGoodsOnlineStatus(form.getGoodsOnlineStatus());

        Integer result = goodsExtManager.updateByQuerySelective(goodsDO,query);

        if(result>=0){
            return GeneralResult.success(Boolean.TRUE);
        }
        return GeneralResult.create(Boolean.FALSE);
    }

    /**
     * 查询用户的购买记录
     * @param userId 用户ID
     * @return 购买记录
     */
    @Override
    public GeneralResult<List<BuyGoodsRecordDTO>> queryBuyGoodsRecord(Long userId) {
        if(Objects.isNull(userId)){
            return GeneralResult.error("","用户ID为空");
        }
        List<BuyGoodsDO> buyGoodsDOS = queryBuyGoodsList(userId);
        if(CollectionUtils.isEmpty(buyGoodsDOS)){
            return GeneralResult.create(new ArrayList<>());
        }
        List<BuyGoodsRecordDTO> buyGoodsRecordDTOS = new ArrayList<>();
        buyGoodsDOS.forEach(buyGoodsDO -> {
            BuyGoodsRecordDTO buyGoodsRecordDTO = BeanCopierUtil.convert(buyGoodsDO,BuyGoodsRecordDTO.class);
            GoodsDO goodsDO = goodsMapper.selectByPrimaryKey(buyGoodsDO.getGoodsId());
            buyGoodsRecordDTO.setEmployStatusDes(EmployStatusEnum.getMsgByCode(buyGoodsDO.getEmployStatus()));
            buyGoodsRecordDTO.setGmtCreate(DateUtil.ofLocalDateTime(buyGoodsDO.getGmtCreate()).toString());
            buyGoodsRecordDTO.setGoodsName(goodsDO.getGoodsName());
            buyGoodsRecordDTOS.add(buyGoodsRecordDTO);
        });
        return GeneralResult.success(buyGoodsRecordDTOS);
    }

    /**
     * 根据用户id 查询用户的购买记录详情
     * @param userId
     * @return
     */
    @Override
    public List<BuyGoodsDO> queryBuyGoodsList(Long userId) {
        if(Objects.isNull(userId)){
            return Collections.emptyList();
        }
        BuyGoodsQuery query = new BuyGoodsQuery();
        query.createCriteria().andUserIdEqualTo(userId)
                .andStatusEqualTo(RecordStatusEnum.VALID.getCode());
        return buyGoodsExtManager.selectByQuery(query);
    }

    /**
     * 增加购买商品
     * @param form 商品信息
     * @return
     */
    @Override
    public GeneralResult<Boolean> addBuyGoods(BuyGoodsForm form) {
        //创建购买记录
        BuyGoodsDO buyGoodsDO = BeanCopierUtil.convert(form,BuyGoodsDO.class);
        Integer result =  buyGoodsManager.insertSelective(buyGoodsDO);
        if(result != 0){
            return GeneralResult.success(Boolean.TRUE);
        }
        return GeneralResult.error("","购买失败");
    }
}
