package com.taofeng.webcast.service.impl;

import com.taofeng.webcast.common.DTO.AssetManagementDTO;
import com.taofeng.webcast.common.DTO.AssetManagementDetailDTO;
import com.taofeng.webcast.common.Enum.ErrorCodeEnum;
import com.taofeng.webcast.common.Enum.RecordStatusEnum;
import com.taofeng.webcast.common.entity.GeneralResult;
import com.taofeng.webcast.common.entity.PageResult;
import com.taofeng.webcast.common.form.AssetDetailsForm;
import com.taofeng.webcast.common.form.AssetManagementForm;
import com.taofeng.webcast.common.util.BeanCopierUtil;
import com.taofeng.webcast.dao.domain.AssetManagementDO;
import com.taofeng.webcast.dao.domain.AssetManagementDetailDO;
import com.taofeng.webcast.dao.domain.UserDO;
import com.taofeng.webcast.dao.manager.Ext.IAssetManagermentDetailExtManager;
import com.taofeng.webcast.dao.manager.Ext.IAssetManagermentExtManger;
import com.taofeng.webcast.dao.manager.Ext.IUserExtManager;
import com.taofeng.webcast.dao.query.AssetManagementDetailQuery;
import com.taofeng.webcast.dao.query.AssetManagementQuery;
import com.taofeng.webcast.dao.query.UserQuery;
import com.taofeng.webcast.service.IAssetManagementService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * <p>资产管理</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/3/13 下午2:58
 * @since V1.0
 */
@Service
@Slf4j
public class AssetManagementServiceImpl implements IAssetManagementService {

    @Autowired
    private IAssetManagermentExtManger assetManagermentExtManger;
    @Autowired
    private IUserExtManager userExtManager;
    @Autowired
    private IAssetManagermentDetailExtManager assetManagermentDetailExtManager;

    /**
     * 查询资产列表
     * @param form
     * @return
     */
    @Override
    public GeneralResult<PageResult<AssetManagementDTO>> queryAssetList(AssetManagementForm form) {
        if(Objects.isNull(form)){
            log.error("查询资产时,条件入参暂无数据");
            return GeneralResult.error(ErrorCodeEnum.NO_DATA.getErrorCode(),"查询资产时,条件入参暂无数据");
        }

        PageResult<AssetManagementDTO> result = null;
        if(Objects.isNull(form.getAssetNo()) && Objects.isNull(form.getUserNo())){
            result = queryAsset(form,null);
        }else{
            result = queryAssetByCondition(form);
        }
        if(Objects.isNull(result)){
            return GeneralResult.error("","暂无数据");
        }
        return GeneralResult.success(result);
    }

    /**
     * 根据条件查询出资产详情
     * @param form
     * @return
     */
    private PageResult<AssetManagementDTO> queryAssetByCondition(AssetManagementForm form){

        PageResult<AssetManagementDTO> result = null;
        if(!Objects.isNull(form.getAssetNo())){
            result = queryAsset(form,null);
        }
        if(!Objects.isNull(form.getUserNo())){
            List<UserDO> userDOS = userExtManager.selectUserByKeyLike(null,"'%"+form.getUserNo()+"%'");
            List<Long> userIds = null;
            if(!CollectionUtils.isEmpty(userDOS)){
                userIds = userDOS.stream().map(UserDO::getUserId).collect(Collectors.toList());
            }else{
                return null;
            }
            result = queryAsset(form,userIds);
        }
        if(Objects.isNull(result)){
            return null;
        }
        return result;
    }

    /**
     * 查询资产无条件查询
     * @param form
     * @return
     */
    private PageResult<AssetManagementDTO> queryAsset(AssetManagementForm form,List<Long> userIdList){

        AssetManagementQuery query = new AssetManagementQuery();
        query.setPageNo(form.getPageNo());
        query.setPageSize(form.getPageSize());
        AssetManagementQuery.Criteria criteria = query.createCriteria();
        criteria.andStatusEqualTo(RecordStatusEnum.VALID.getCode());
        if(!Objects.isNull(form.getAssetNo())){
             criteria.andAssetNOLike("%"+form.getAssetNo()+"%");
        }
        if(!CollectionUtils.isEmpty(userIdList)){
            criteria.andUserIdIn(userIdList);
        }
        List<AssetManagementDO> assetManagementDOS = assetManagermentExtManger.selectByQuery(query);
        if(CollectionUtils.isEmpty(assetManagementDOS)){
            return null;
        }
        //获取订单的UserIds
        List<Long> userIds = assetManagementDOS.stream().map(AssetManagementDO::getUserId).collect(Collectors.toList());
        UserQuery userQuery = new UserQuery();
        userQuery.createCriteria().andUserIdIn(userIds)
               .andStatusEqualTo(RecordStatusEnum.VALID.getCode());
        //查询资产人的详细信息
        List<UserDO> userDOS = userExtManager.selectByQuery(userQuery);
        if(CollectionUtils.isEmpty(userDOS)){
            return null;
        }
        Map<Long,UserDO> userDOMap = userDOS.stream().collect(Collectors.toMap(UserDO::getUserId, Function.identity()));

        List<AssetManagementDTO> assetManagementDTOS = new ArrayList<>();
        assetManagementDOS.forEach(assetManagementDO -> {
            AssetManagementDTO assetManagementDTO = BeanCopierUtil.convert(assetManagementDO,AssetManagementDTO.class);
            if(userDOMap.containsKey(assetManagementDO.getUserId())){
                assetManagementDTO.setUserNo(userDOMap.get(assetManagementDO.getUserId()).getUserNo());
            }
            assetManagementDTOS.add(assetManagementDTO);
        });

        PageResult<AssetManagementDTO> result = new PageResult<>();
        result.setPageSize(form.getPageSize());
        result.setPageNo(form.getPageNo());
        result.setTotalCount(assetManagermentExtManger.countByQuery(query));
        result.setResult(assetManagementDTOS);
        return result;
    }


    /**
     * 查询资产详情
     * @param form
     * @return
     */
    @Override
    public GeneralResult<PageResult<AssetManagementDetailDTO>> queryAssetListDetails(AssetDetailsForm form) {
        if(Objects.isNull(form)){
            log.error("查询资产详情时,条件入参暂无数据");
            return GeneralResult.error(ErrorCodeEnum.NO_DATA.getErrorCode(),"查询资产详情时,条件入参暂无数据");
        }
        AssetManagementDetailQuery query = new AssetManagementDetailQuery();
        query.setPageNo(form.getPageNo());
        query.setPageSize(form.getPageSize());
        AssetManagementDetailQuery.Criteria criteria = query.createCriteria();
        criteria.andAssetIdEqualTo(form.getAssetId());
        if(!Objects.isNull(form.getYearId())){
              criteria.andYearDescriptionEqualTo(form.getYearId());
        }
        if(!Objects.isNull(form.getMonthId())){
            criteria.andMonthDescriptionEqualTo(form.getMonthId());
        }
        if(!Objects.isNull(form.getDayId())){
            criteria.andDayDescriptionEqualTo(form.getDayId());
        }
        List<AssetManagementDetailDO> assetManagementDetailDOS = assetManagermentDetailExtManager.selectByQuery(query);

        if(CollectionUtils.isEmpty(assetManagementDetailDOS)){
            return GeneralResult.error("","暂无数据");
        }

        List<AssetManagementDetailDTO> assetManagementDetailDTOS = new ArrayList<>();
        assetManagementDetailDOS.forEach(assetManagementDetailDO -> {
            AssetManagementDetailDTO assetManagementDetailDTO = BeanCopierUtil.convert(assetManagementDetailDO,AssetManagementDetailDTO.class);
            assetManagementDetailDTOS.add(assetManagementDetailDTO);
        });

        PageResult<AssetManagementDetailDTO> result = new PageResult<>();
        result.setPageSize(form.getPageSize());
        result.setPageNo(form.getPageNo());
        result.setTotalCount(assetManagermentDetailExtManager.countByQuery(query));
        result.setResult(assetManagementDetailDTOS);

        return GeneralResult.success(result);
    }

    /**
     * 查询用户信息
     * @param userId 用户id
     * @return 资产详情
     */
    @Override
    public GeneralResult<List<AssetManagementDetailDTO>> queryAssetDetailBy(Long userId) {

        //根据用户id查询用户资产id
        AssetManagementDO assetManagementDO = queryAssetInfoBy(userId);
        if(Objects.isNull(assetManagementDO)){
            return GeneralResult.error("","该主播暂无资产");
        }
        AssetManagementDetailQuery query = new AssetManagementDetailQuery();
        query.createCriteria().andAssetIdEqualTo(assetManagementDO.getAssetId())
                      .andStatusEqualTo(RecordStatusEnum.VALID.getCode());
        List<AssetManagementDetailDO> assetManagementDetailDOS = assetManagermentDetailExtManager.selectByQuery(query);
        if(CollectionUtils.isEmpty(assetManagementDetailDOS)){
            return GeneralResult.error("","暂无数据");
        }
        List<AssetManagementDetailDTO> assetManagementDetailDTOS = new ArrayList<>();
        assetManagementDetailDOS.forEach(assetManagementDetailDO -> {
            AssetManagementDetailDTO assetManagementDetailDTO = BeanCopierUtil.convert(assetManagementDetailDO,AssetManagementDetailDTO.class);
            assetManagementDetailDTOS.add(assetManagementDetailDTO);
        });
        return GeneralResult.success(assetManagementDetailDTOS);
    }

    /**
     * 根据用户id查询主播资产
     * @param userId
     * @return
     */
    @Override
    public AssetManagementDO queryAssetInfoBy(Long userId) {
        if(Objects.isNull(userId)){
            return null;
        }
        AssetManagementQuery query = new AssetManagementQuery();
        query.createCriteria().andStatusEqualTo(RecordStatusEnum.VALID.getCode())
                .andUserIdEqualTo(userId);
        List<AssetManagementDO> assetManagementDOS = assetManagermentExtManger.selectByQuery(query);
        if(CollectionUtils.isEmpty(assetManagementDOS)){
            return null;
        }
        return assetManagementDOS.get(0);
    }
}
