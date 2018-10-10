package com.taofeng.webcast.service.impl;

import com.taofeng.webcast.common.DTO.AnchorDTO;
import com.taofeng.webcast.common.Enum.ErrorCodeEnum;
import com.taofeng.webcast.common.Enum.RecordStatusEnum;
import com.taofeng.webcast.common.Enum.SexEnum;
import com.taofeng.webcast.common.entity.GeneralResult;
import com.taofeng.webcast.common.entity.PageResult;
import com.taofeng.webcast.common.form.AnchorForm;
import com.taofeng.webcast.common.form.AuditAnchorForm;
import com.taofeng.webcast.common.util.BeanCopierUtil;
import com.taofeng.webcast.common.util.DateUtil;
import com.taofeng.webcast.dao.domain.NetworkAnchorDO;
import com.taofeng.webcast.dao.domain.NetworkBroadcastTypeDetailsDO;
import com.taofeng.webcast.dao.domain.UserDO;
import com.taofeng.webcast.dao.manager.Ext.INetworkAnchorExtManager;
import com.taofeng.webcast.dao.manager.Ext.IUserExtManager;
import com.taofeng.webcast.dao.manager.INetworkBroadcastTypeDetailsManager;
import com.taofeng.webcast.dao.query.NetworkAnchorQuery;
import com.taofeng.webcast.service.IAnchorManagementService;
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
 * <p>网络主播管理实现页面</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/3/12 下午4:31
 * @since V1.0
 */
@Service
public class AnchorManagementServiceImpl implements IAnchorManagementService {

    @Autowired
    private IUserExtManager extManager;
    @Autowired
    private INetworkAnchorExtManager networkAnchorExtManager;
    @Autowired
    private INetworkBroadcastTypeDetailsManager networkBroadcastTypeDetailsManager;

    /**
     * 查询网络主播的申请记录
     * @param form
     * @return
     */
    @Override
    public GeneralResult<PageResult<AnchorDTO>> queryAnchorApplyRecord(AnchorForm form) {
        if(Objects.isNull(form)){
            return GeneralResult.error(ErrorCodeEnum.NO_DATA.getErrorMeg(),"查询网络主播信息,查询条件为空 ");
        }
        //设置查询条件
        List<UserDO> userDOS = queryUser(form);
        List<Long> userIds = null;
        if(!CollectionUtils.isEmpty(userDOS)){
            userIds = userDOS.stream().distinct().map(UserDO::getUserId).collect(Collectors.toList());
        }else{
            return GeneralResult.error("","暂无数据");
        }
        NetworkAnchorQuery query = new NetworkAnchorQuery();
        query.setPageNo(form.getPageNo());
        query.setPageSize(form.getPageSize());
        query=buildNetworkAnchorQuery(query,form,userIds);
        List<NetworkAnchorDO> networkAnchorDOS = networkAnchorExtManager.selectByQuery(query);
        if(CollectionUtils.isEmpty(networkAnchorDOS)){
             return GeneralResult.error("","暂无数据");
        }
        Map<Long,UserDO> userDOMap = userDOS.stream().collect(Collectors.toMap(UserDO::getUserId, Function.identity()));

        PageResult<AnchorDTO> result = new PageResult<>();
        result.setPageNo(form.getPageNo());
        result.setPageSize(form.getPageSize());
        result.setTotalCount(networkAnchorExtManager.countByQuery(query));
        List<AnchorDTO> anchorDTOS = new ArrayList<>();
        networkAnchorDOS.forEach(networkAnchorDO -> {
            AnchorDTO anchorDTO =  BeanCopierUtil.convert(networkAnchorDO,AnchorDTO.class);
            NetworkBroadcastTypeDetailsDO detailsDO = networkBroadcastTypeDetailsManager.selectByPrimaryKey(anchorDTO.getNetworkBroadcastTypeDetailId());
            anchorDTO.setTypeDetailName(detailsDO.getTypeDetailName());
            if(userDOMap.containsKey(networkAnchorDO.getUserId())){
                BeanCopierUtil.copy(userDOMap.get(networkAnchorDO.getUserId()),anchorDTO);
                anchorDTO.setUserSexDesc(SexEnum.getMegByCode(userDOMap.get(networkAnchorDO.getUserId()).getUserSex()));
                anchorDTO.setGmtCreate(userDOMap.get(networkAnchorDO.getUserId()).getGmtCreate().toString().substring(0,10));
            }
            anchorDTOS.add(anchorDTO);
        });
        if(!CollectionUtils.isEmpty(anchorDTOS)){
            result.setResult(anchorDTOS);
        }
        return GeneralResult.create(result);
    }

    /**
     * 查询出所有的用户信息
     * @param form
     * @return
     */
    private List<UserDO> queryUser(AnchorForm form){
        List<UserDO> userDOS = null;
        if(!Objects.isNull(form.getUserName()) && !Objects.isNull(form.getUserNO())) {
            userDOS = extManager.selectUserByKeyLike("'%" + form.getUserName() + "%'", "'%" + form.getUserNO() + "%'");
        }else if(Objects.isNull(form.getUserName()) && !Objects.isNull(form.getUserNO())){
            userDOS = extManager.selectUserByKeyLike(form.getUserName() , "'%" + form.getUserNO() + "%'");
        }else if(!Objects.isNull(form.getUserName()) && Objects.isNull(form.getUserNO())){
            userDOS = extManager.selectUserByKeyLike("'%" + form.getUserName() + "%'", form.getUserNO());
        }else{
            userDOS = extManager.selectUserByKeyLike(form.getUserName(),form.getUserNO());
        }
        return userDOS;
    }

    /**
     * 构建NetworkAnchorQuery
     * @param query
     * @param form
     * @return
     */
    private NetworkAnchorQuery buildNetworkAnchorQuery(NetworkAnchorQuery query,AnchorForm form,List<Long> userIds){

        NetworkAnchorQuery.Criteria  criteria = query.createCriteria();

        //根据用户名称和用户用户编号进行模糊查询
        if(!CollectionUtils.isEmpty(userIds)){
            criteria.andUserIdIn(userIds);
        }
        //根据审核状态进行查询
        if(!Objects.isNull(form.getAuditStatus())){
            criteria.andAuditStatusEqualTo(form.getAuditStatus());
        }
        //根据申请时间进行查询
        if(!Objects.isNull(form.getDate())){
            //将String类型的日期装换成字符串
            criteria.andGmtCreateEqualTo(DateUtil.ofLocalDateTime(DateUtil.convertStringToDateYMD(form.getDate())));
        }
        criteria.andStatusEqualTo(RecordStatusEnum.VALID.getCode());
        return query;
    }


    /**
     * 审核网络主播
     * @param form
     * @return
     */
    @Override
    public GeneralResult<Boolean> auditAnchor(AuditAnchorForm form) {
        if(Objects.isNull(form)){
            return GeneralResult.error(ErrorCodeEnum.NO_DATA.getErrorMeg(),"审核网络主播时条件为空");
        }
        NetworkAnchorQuery query = new NetworkAnchorQuery();
        query.createCriteria()
                .andUserIdEqualTo(form.getUserId())
                .andStatusEqualTo(RecordStatusEnum.VALID.getCode());
        //对数据库中
        NetworkAnchorDO networkAnchorDO = new NetworkAnchorDO();
        networkAnchorDO.setAuditStatus(form.getAuditStatus());

        Integer result = networkAnchorExtManager.updateByQuerySelective(networkAnchorDO,query);

        if(result>=0){
            return GeneralResult.success(Boolean.TRUE);
        }
        return GeneralResult.create(Boolean.FALSE);
    }

}
