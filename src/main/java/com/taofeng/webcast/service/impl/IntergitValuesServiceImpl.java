package com.taofeng.webcast.service.impl;

import com.taofeng.webcast.common.DTO.ComplainDTO;
import com.taofeng.webcast.common.DTO.ComplainRecordDTO;
import com.taofeng.webcast.common.DTO.IntegerRecordDTO;
import com.taofeng.webcast.common.DTO.IntergitValuesDTO;
import com.taofeng.webcast.common.Enum.*;
import com.taofeng.webcast.common.entity.GeneralResult;
import com.taofeng.webcast.common.entity.PageResult;
import com.taofeng.webcast.common.form.IntergitValuesForm;
import com.taofeng.webcast.common.form.UpdateIntergitValuesForm;
import com.taofeng.webcast.common.util.BeanCopierUtil;
import com.taofeng.webcast.dao.domain.IntergityValuesDO;
import com.taofeng.webcast.dao.domain.UserDO;
import com.taofeng.webcast.dao.manager.Ext.IUserExtManager;
import com.taofeng.webcast.dao.mapper.ext.IntergityValuesExtMapper;
import com.taofeng.webcast.dao.query.IntergityValuesQuery;
import com.taofeng.webcast.dao.query.UserQuery;
import com.taofeng.webcast.service.IComplainManagementService;
import com.taofeng.webcast.service.IntergitValuesService;
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
 * <p>诚信值管理</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/3/13 上午11:56
 * @since V1.0
 */
@Service
@Slf4j
public class IntergitValuesServiceImpl implements IntergitValuesService{

    @Autowired
    private IUserExtManager userExtManager;
    @Autowired
    private IntergityValuesExtMapper intergityValuesExtMapper;
    @Autowired
    private IComplainManagementService complainManagementService;

    /**
     * 查询诚信值的列表
     * @param form
     * @return
     */
    @Override
    public GeneralResult<PageResult<IntergitValuesDTO>> queryIntergitValuesList(IntergitValuesForm form) {
        if(Objects.isNull(form)) {
            log.error("查询诚信值时,条件入参暂无数据");
            return GeneralResult.error(ErrorCodeEnum.NO_DATA.getErrorCode(), "查询诚信值时,条件入参暂无数据");
        }
        IntergityValuesQuery query = new IntergityValuesQuery();
        query.setPageNo(form.getPageNo());
        query.setPageSize(form.getPageSize());
        IntergityValuesQuery.Criteria criteria = query.createCriteria();

        //拼接查询条件
        List<Long> userIds = queryUserIds(form);
        buildIntergityValuesQuery(criteria,form,userIds);

        List<IntergityValuesDO> intergitValuesDOS = intergityValuesExtMapper.selectByQuery(query);
        if(CollectionUtils.isEmpty(intergitValuesDOS)){
            return GeneralResult.error(ErrorCodeEnum.NO_DATA.getErrorCode(),"暂无数据");
        }
        //得到map
        Map<Long,UserDO> userDOMap = queryUserDOToMap(intergitValuesDOS);
        if(Objects.isNull(userDOMap)){
            return GeneralResult.error(ErrorCodeEnum.NO_DATA.getErrorCode(),"暂无数据");
        }
        //拼接出参数据
        List<IntergitValuesDTO> intergitValuesDTOS = buildIntergitValuesDTO(intergitValuesDOS,userDOMap);
        if(CollectionUtils.isEmpty(intergitValuesDTOS)){
            return GeneralResult.error(ErrorCodeEnum.NO_DATA.getErrorCode(),"暂无数据");
        }

        PageResult<IntergitValuesDTO> result = new PageResult<>();
        result.setPageNo(form.getPageNo());
        result.setPageSize(form.getPageSize());
        result.setTotalCount(intergityValuesExtMapper.countByQuery(query));
        result.setResult(intergitValuesDTOS);
        return GeneralResult.success(result);
    }

    /**
     * 查询诚信值的主要的扣分项
     * @param userId 用户ID
     * @return 诚信值记录
     */
    @Override
    public GeneralResult<IntegerRecordDTO> queryIntergitValuesRecord(Long userId) {

        //诚信值记录
        IntergityValuesDO intergityValuesDO = queryIntergitValuesBy(userId);
        //查询用户的投诉记录
        GeneralResult<List<ComplainDTO>> result = complainManagementService.queryComplainDetailBy(userId, ComplainStatusEnum.DEFENDANT.getCode());
        List<ComplainDTO> complainDTOS = new ArrayList<>();
        if(result.isSuccess()){
            complainDTOS = result.getData();
        }else{
            log.info("查询用户投诉信息出错{}",result.getMessage());
        }
        IntegerRecordDTO integerRecordDTO = new IntegerRecordDTO();
        integerRecordDTO.setValues(intergityValuesDO.getValueNum());

        List<ComplainRecordDTO> complainRecordDTOS = new ArrayList<>();
        complainDTOS.forEach(complainDTO -> {
            if(!Objects.isNull(complainDTO.getComplainRank())) {
                ComplainRecordDTO complainRecordDTO = new ComplainRecordDTO();
                complainRecordDTO.setReason(complainDTO.getReason());
                complainRecordDTO.setDeductValue(ComplainGradeEnum.getMsgByCode(ComplainRankEnum.valueOf(complainDTO.getComplainRank())));
                complainRecordDTO.setTime(complainDTO.getComplainDate());
                complainRecordDTOS.add(complainRecordDTO);
            }
        });
        integerRecordDTO.setComplainRecordDTOS(complainRecordDTOS);
        return GeneralResult.create(integerRecordDTO);
    }



    /**
     *拼接出参数据
     * @param intergitValuesDOS
     * @param userDOMap
     * @return
     */
    private List<IntergitValuesDTO> buildIntergitValuesDTO(List<IntergityValuesDO> intergitValuesDOS, Map<Long,UserDO> userDOMap ){
        List<IntergitValuesDTO> intergitValuesDTOS = new ArrayList<>();
        intergitValuesDOS.forEach(intergityValuesDO -> {
            IntergitValuesDTO intergitValuesDTO = new IntergitValuesDTO();
            if(userDOMap.containsKey(intergityValuesDO.getUserId())){
                BeanCopierUtil.copy(userDOMap.get(intergityValuesDO.getUserId()),intergitValuesDTO);
                intergitValuesDTO.setUserTypeDesc(UserTypeEnum.getMessage(userDOMap.get(intergityValuesDO.getUserId()).getUserType()));
            }
            intergitValuesDTO.setIntergityId(intergityValuesDO.getIntergityId());
            intergitValuesDTO.setValue(intergityValuesDO.getValueNum());
            intergitValuesDTOS.add(intergitValuesDTO);
        });
        return intergitValuesDTOS;
    }


    /**
     * 查询出userIds
     * @param form
     * @return
     */
    private List<Long> queryUserIds(IntergitValuesForm form){
        //模糊查询出对应的userDO
        List<UserDO> userDOS = null;

        if(!Objects.isNull(form.getUserName()) && !"".equals(form.getUserName())){
            userDOS = userExtManager.selectUserByKeyLike("'%"+form.getUserName()+"%'",null);
        }
        if(!Objects.isNull(form.getUserNO()) && !"".equals(form.getUserNO())){
            userDOS = userExtManager.selectUserByKeyLike(null,"'%"+form.getUserNO()+"%'");
        }
        if(!Objects.isNull(form.getUserType()) && !"".equals(form.getUserType()) ){
            userDOS = userExtManager.selectUserIdByUserType(form.getUserType());
        }
        List<Long> userIds = null;
        if(!CollectionUtils.isEmpty(userDOS)){
            userIds = userDOS.stream().map(UserDO::getUserId).collect(Collectors.toList());
        }
        return userIds;
    }

    /**
     * 组合uesrId和userDO之间的Map
     * @param intergityValuesDOS
     * @return
     */
    private Map<Long,UserDO> queryUserDOToMap(List<IntergityValuesDO> intergityValuesDOS){

        if(CollectionUtils.isEmpty(intergityValuesDOS)){
            return  null;
        }
        List<Long> userIds = intergityValuesDOS.stream().map(IntergityValuesDO::getUserId).collect(Collectors.toList());

        UserQuery query = new UserQuery();
        query.createCriteria().andUserIdIn(userIds)
                .andStatusEqualTo(RecordStatusEnum.VALID.getCode());
        List<UserDO> userDOS = userExtManager.selectByQuery(query);
        if(CollectionUtils.isEmpty(userDOS)){
            return null;
        }
        Map<Long,UserDO> userDOMap = userDOS.stream().collect(Collectors.toMap(UserDO::getUserId,Function.identity()));
        return userDOMap;
    }

    /**
     * 拼接查询条件
     * @param criteria
     * @param form
     * @param userIds
     */
    private void buildIntergityValuesQuery(IntergityValuesQuery.Criteria criteria,IntergitValuesForm form,List<Long> userIds){
        criteria.andStatusEqualTo(RecordStatusEnum.VALID.getCode());
        if(!CollectionUtils.isEmpty(userIds)){
              criteria.andUserIdIn(userIds);
          }
          if(!Objects.isNull(form.getUnderValue()) && !Objects.isNull(form.getUpValue())){
               criteria.andValueNumBetween(form.getUpValue(),form.getUnderValue());
          }else if(Objects.isNull(form.getUnderValue()) && !Objects.isNull(form.getUpValue())){
              criteria.andValueNumGreaterThanOrEqualTo(form.getUpValue());
          }else if(!Objects.isNull(form.getUnderValue()) && Objects.isNull(form.getUpValue())){
              criteria.andValueNumBetween(0D,form.getUnderValue());
          }
    }

    /**
     * 修改诚信值
     * @param form
     * @return
     */
    @Override
    public GeneralResult<Boolean> updateIntergitValues(UpdateIntergitValuesForm form) {
        if(Objects.isNull(form)){
            log.error("查询诚信值时,条件入参暂无数据");
            return GeneralResult.error(ErrorCodeEnum.NO_DATA.getErrorCode(),"修改诚信值时,条件入参暂无数据");
        }
        //设置Do
        IntergityValuesDO intergityValuesDO = new IntergityValuesDO();
        intergityValuesDO.setValueNum(form.getValue());

        //设置查询条件
        IntergityValuesQuery query = new IntergityValuesQuery();
        query.createCriteria().andIntergityIdEqualTo(form.getIntergityId())
                .andStatusEqualTo(RecordStatusEnum.VALID.getCode());

        Integer result = intergityValuesExtMapper.updateByQuerySelective(intergityValuesDO,query);
        if(result>=1){
            return GeneralResult.success(Boolean.TRUE);
        }
        return GeneralResult.error("","更新失败");
    }

    /**
     * 根据用户ID查询某一用户的诚信值信息
     * @param userId 用户ID
     * @return 诚信值记录
     */
    @Override
    public IntergityValuesDO queryIntergitValuesBy(Long userId) {
        if(Objects.isNull(userId)){
            return new IntergityValuesDO();
        }
        IntergityValuesQuery query = new IntergityValuesQuery();
        query.createCriteria().andUserIdEqualTo(userId)
                .andStatusEqualTo(RecordStatusEnum.VALID.getCode());
        List<IntergityValuesDO> intergityValuesDOS = intergityValuesExtMapper.selectByQuery(query);
        if(CollectionUtils.isEmpty(intergityValuesDOS)){
            return new IntergityValuesDO();
        }
        return intergityValuesDOS.get(0);
    }
}
