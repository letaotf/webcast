package com.taofeng.webcast.service.impl;

import com.taofeng.webcast.common.DTO.NetworkBroadcastDTO;
import com.taofeng.webcast.common.Enum.ErrorCodeEnum;
import com.taofeng.webcast.common.Enum.RecordStatusEnum;
import com.taofeng.webcast.common.entity.GeneralResult;
import com.taofeng.webcast.common.entity.PageResult;
import com.taofeng.webcast.common.form.AddNetworkBroadcastForm;
import com.taofeng.webcast.common.form.DeleteNetworkBroadcastForm;
import com.taofeng.webcast.common.form.NetworkBroadcastForm;
import com.taofeng.webcast.common.form.UpdateNetworkBroadcastForm;
import com.taofeng.webcast.common.util.BeanCopierUtil;
import com.taofeng.webcast.common.util.CreatOrderNoUtil;
import com.taofeng.webcast.dao.domain.NetworkBroadcastTypeDetailsDO;
import com.taofeng.webcast.dao.manager.Ext.INetworkBroadcastTypeDetailsExtManager;
import com.taofeng.webcast.dao.manager.INetworkBroadcastTypeDetailsManager;
import com.taofeng.webcast.dao.query.NetworkBroadcastTypeDetailsQuery;
import com.taofeng.webcast.service.INetworkBroadcastTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * <p>节目管理</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/3/13 下午2:32
 * @since V1.0
 */
@Service
@Slf4j
public class NetworkBroadcastTypeServiceImpl implements INetworkBroadcastTypeService{


    @Autowired
    private INetworkBroadcastTypeDetailsExtManager networkBroadcastTypeDetailsExtManager;
    @Autowired
    private INetworkBroadcastTypeDetailsManager networkBroadcastTypeDetailsManager;

    /**
     * 查询节目清单列表
     * @param form
     * @return
     */
    @Override
    public GeneralResult<PageResult<NetworkBroadcastDTO>> queryNetworkBroadcastType(NetworkBroadcastForm form) {
        if(Objects.isNull(form)){
            log.error("查询节目清单时,条件入参暂无数据");
            return GeneralResult.error(ErrorCodeEnum.NO_DATA.getErrorCode(),"查询节目清单时,条件入参暂无数据");
        }
        NetworkBroadcastTypeDetailsQuery query = new NetworkBroadcastTypeDetailsQuery();
        query.setPageNo(form.getPageNo());
        query.setPageSize(form.getPageSize());

        NetworkBroadcastTypeDetailsQuery.Criteria criteria = query.createCriteria();
        if(!Objects.isNull(form.getTypeId())){
            criteria.andTypeIdEqualTo(form.getTypeId());
        }
        criteria.andStatusEqualTo(RecordStatusEnum.VALID.getCode());

        List<NetworkBroadcastTypeDetailsDO> networkBroadcastTypeDetailsDOS = networkBroadcastTypeDetailsExtManager.selectByQuery(query);
        if(CollectionUtils.isEmpty(networkBroadcastTypeDetailsDOS)){
            return GeneralResult.error("","暂无数据");
        }

        PageResult<NetworkBroadcastDTO> result = new PageResult<>();
        result.setPageNo(form.getPageNo());
        result.setPageSize(form.getPageSize());
        result.setTotalCount(networkBroadcastTypeDetailsExtManager.countByQuery(query));
        List<NetworkBroadcastDTO> networkBroadcastDTOS = new ArrayList<>();
        networkBroadcastTypeDetailsDOS.forEach(networkBroadcastTypeDetailsDO -> {
            NetworkBroadcastDTO networkBroadcastDTO = BeanCopierUtil.convert(networkBroadcastTypeDetailsDO,NetworkBroadcastDTO.class);
            networkBroadcastDTO.setGmtCreate(networkBroadcastTypeDetailsDO.getGmtCreate().toString().replace("T"," "));
            networkBroadcastDTOS.add(networkBroadcastDTO);
        });
        result.setResult(networkBroadcastDTOS);
        return GeneralResult.success(result);
    }

    /**
     * 查询节目清单详情
     * @param networkBroadcastTypeDetailId
     * @return
     */
    @Override
    public GeneralResult<NetworkBroadcastDTO> queryNetworkBroadcastTypeDetail(Long networkBroadcastTypeDetailId) {

        NetworkBroadcastTypeDetailsQuery query = new NetworkBroadcastTypeDetailsQuery();
        NetworkBroadcastTypeDetailsQuery.Criteria criteria = query.createCriteria();
        criteria.andNetworkBroadcastTypeDetailIdEqualTo(networkBroadcastTypeDetailId);
        criteria.andStatusEqualTo(RecordStatusEnum.VALID.getCode());

        List<NetworkBroadcastTypeDetailsDO> networkBroadcastTypeDetailsDOS = networkBroadcastTypeDetailsExtManager.selectByQuery(query);
        if(CollectionUtils.isEmpty(networkBroadcastTypeDetailsDOS)){
            return GeneralResult.error("","暂无数据");
        }
        NetworkBroadcastDTO networkBroadcastDTO = BeanCopierUtil.convert(networkBroadcastTypeDetailsDOS.get(0),NetworkBroadcastDTO.class);
        return GeneralResult.success(networkBroadcastDTO);
    }

    /**
     * 添加节目
     * @param form
     * @return
     */
    @Override
    public GeneralResult<Boolean> addNetworkBroadcastType(AddNetworkBroadcastForm form) {
        if(Objects.isNull(form)){
            log.error("节目添加清单时,条件入参暂无数据");
            return GeneralResult.error(ErrorCodeEnum.NO_DATA.getErrorCode(),"查询节目清单时,条件入参暂无数据");
        }
        //生成节目编号
        String networkTypeNo = CreatOrderNoUtil.createNetworkBroadCastNO(new Date());

        NetworkBroadcastTypeDetailsDO detailsDO = new NetworkBroadcastTypeDetailsDO();
        detailsDO.setTypeId(form.getTypeId());
        detailsDO.setBriefIntroduction(form.getBriefIntroduction());
        detailsDO.setNetworkTypeNo(networkTypeNo);
        detailsDO.setTypeDetailName(form.getTypeDetailName());
        Long result = networkBroadcastTypeDetailsManager.insertSelective(detailsDO);
        if(result>=1){
            return GeneralResult.success(Boolean.TRUE);
        }
        return GeneralResult.create(Boolean.FALSE);
    }

    /**
     * 更新节目
     * @param form
     * @return
     */
    @Override
    public GeneralResult<Boolean> updateNetworkBroadcastType(UpdateNetworkBroadcastForm form) {
        if(Objects.isNull(form)){
            log.error("更新添加清单时,条件入参暂无数据");
            return GeneralResult.error(ErrorCodeEnum.NO_DATA.getErrorCode(),"更新节目清单时,条件入参暂无数据");
        }
        NetworkBroadcastTypeDetailsDO detailsDO = new NetworkBroadcastTypeDetailsDO();
        detailsDO.setBriefIntroduction(form.getBriefIntroduction());
        detailsDO.setTypeDetailName(form.getTypeDetailName());
        detailsDO.setTypeId(form.getTypeId());
        detailsDO.setBriefIntroduction(form.getBriefIntroduction());

        NetworkBroadcastTypeDetailsQuery query = new NetworkBroadcastTypeDetailsQuery();
        query.createCriteria().andNetworkBroadcastTypeDetailIdEqualTo(form.getNetworkBroadcastTypeDetailId())
                .andStatusEqualTo(RecordStatusEnum.VALID.getCode());

        Integer result = networkBroadcastTypeDetailsExtManager.updateByQuerySelective(detailsDO,query);

        if(result>=1){
            return GeneralResult.success(Boolean.TRUE);
        }
        return GeneralResult.create(Boolean.FALSE);
    }

    /**
     * 删除节目
     * @param form
     * @return
     */
    @Override
    public GeneralResult<Boolean> deleteNetworkBroadcastType(DeleteNetworkBroadcastForm form) {
        if(Objects.isNull(form)){
            log.error("删除添加清单时,条件入参暂无数据");
            return GeneralResult.error(ErrorCodeEnum.NO_DATA.getErrorCode(),"删除节目清单时,条件入参暂无数据");
        }
        NetworkBroadcastTypeDetailsDO detailsDO = new NetworkBroadcastTypeDetailsDO();
        detailsDO.setStatus(RecordStatusEnum.NO_VALID.getCode());

        NetworkBroadcastTypeDetailsQuery query = new NetworkBroadcastTypeDetailsQuery();
        query.createCriteria().andNetworkBroadcastTypeDetailIdEqualTo(form.getNetworkBroadcastTypeDetailId())
                .andStatusEqualTo(RecordStatusEnum.VALID.getCode());

        Integer result = networkBroadcastTypeDetailsExtManager.updateByQuerySelective(detailsDO,query);

        if(result>=1){
            return GeneralResult.success(Boolean.TRUE);
        }
        return GeneralResult.create(Boolean.FALSE);
    }
}
