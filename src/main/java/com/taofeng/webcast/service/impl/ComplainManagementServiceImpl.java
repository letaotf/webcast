package com.taofeng.webcast.service.impl;

import com.google.common.eventbus.EventBus;
import com.taofeng.webcast.bus.event.DeductIntergityValuesEvent;
import com.taofeng.webcast.common.DTO.ComplainDTO;
import com.taofeng.webcast.common.Enum.*;
import com.taofeng.webcast.common.entity.GeneralResult;
import com.taofeng.webcast.common.entity.PageResult;
import com.taofeng.webcast.common.form.AddComplainForm;
import com.taofeng.webcast.common.form.ComplainForm;
import com.taofeng.webcast.common.form.DealComplainForm;
import com.taofeng.webcast.common.form.UpdateComplainForm;
import com.taofeng.webcast.common.util.DateUtil;
import com.taofeng.webcast.dao.domain.ComplainDO;
import com.taofeng.webcast.dao.domain.DealComplainDO;
import com.taofeng.webcast.dao.domain.UserDO;
import com.taofeng.webcast.dao.manager.Ext.IComplainExtManager;
import com.taofeng.webcast.dao.manager.Ext.IDealComplainExtManager;
import com.taofeng.webcast.dao.manager.Ext.IUserExtManager;
import com.taofeng.webcast.dao.manager.IComplainManager;
import com.taofeng.webcast.dao.manager.IDealComplainManager;
import com.taofeng.webcast.dao.manager.IUserManager;
import com.taofeng.webcast.dao.query.ComplainQuery;
import com.taofeng.webcast.dao.query.DealComplainQuery;
import com.taofeng.webcast.dao.query.UserQuery;
import com.taofeng.webcast.service.IComplainManagementService;
import com.taofeng.webcast.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * <p>投诉管理</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/3/13 下午1:45
 * @since V1.0
 */
@Service
@Slf4j
public class ComplainManagementServiceImpl implements IComplainManagementService{

    @Autowired
    private IUserExtManager userExtManager;
    @Autowired
    private IComplainManager complainManager;
    @Autowired
    private IUserManager userManager;
    @Autowired
    private IDealComplainManager dealComplainManager;
    @Autowired
    private IDealComplainExtManager dealComplainExtManager;
    @Autowired
    private IComplainExtManager complainExtManager;
    @Autowired
    private IUserService userService;
    @Autowired
    private EventBus eventBus;

    /**
     * 查询投诉列表
     * @param form
     * @return
     */
    @Override
    public GeneralResult<PageResult<ComplainDTO>> queryComplainList(ComplainForm form) {
        if(Objects.isNull(form)){
            log.error("查询诚信值时,条件入参暂无数据");
            return GeneralResult.error(ErrorCodeEnum.NO_DATA.getErrorCode(),"查询诚投诉信息时,条件入参暂无数据");
        }
        PageResult<ComplainDTO> result = null;
        if((Objects.isNull(form.getUserNo()) && Objects.isNull(form.getDefendantUserNo()))
                || ("".equals(form.getUserNo()) && "".equals(form.getDefendantUserNo()))){
            result = queryComplainListNOParm(form);
        }else {
            //模糊查询出对应的userDO
            UserQuery query = new UserQuery();
            UserQuery.Criteria criteria = query.createCriteria();
            List<Long> userIds = null;
            if (!Objects.isNull(form.getUserNo()) && !form.getUserNo().equals("")) {
                criteria.andUserNoLike("%" + form.getUserNo() + "%").andStatusEqualTo(RecordStatusEnum.VALID.getCode());
                userIds = userExtManager.selectUserIdByKeyLike(query);
            }
            if (!Objects.isNull(form.getDefendantUserNo()) && !form.getDefendantUserNo().equals("")) {
                criteria.andUserNoLike("%" + form.getDefendantUserNo() + "%").andStatusEqualTo(RecordStatusEnum.VALID.getCode());
                userIds = userExtManager.selectUserIdByKeyLike(query);
            }
            if (CollectionUtils.isEmpty(userIds)) {
                return GeneralResult.error(ErrorCodeEnum.NO_DATA.getErrorCode(), "暂无数据");
            }
            result = queryComplainDOList(userIds, form.getPageNo(), form.getPageSize());
        }
        if(Objects.isNull(result)){
            return GeneralResult.error(ErrorCodeEnum.NO_DATA.getErrorCode(), "暂无数据");
        }
        return GeneralResult.success(result);
    }

    /**
     * 根据用户Id,进行分页查询
     * @param userIds
     * @return
     */
    private PageResult<ComplainDTO> queryComplainDOList(List<Long> userIds,Integer pageNo,Integer pageSize){

        if(CollectionUtils.isEmpty(userIds)){
            return null;
        }

        PageResult<ComplainDTO> result = new PageResult<>();
        result.setPageNo(pageNo);
        result.setPageSize(pageSize);

        ComplainQuery complainQuery = new ComplainQuery();
        complainQuery.setPageNo(pageNo);
        complainQuery.setPageSize(pageSize);
        complainQuery.createCriteria().andUserIdIn(userIds)
                .andStatusEqualTo(RecordStatusEnum.VALID.getCode());
        List<ComplainDO> complainDOS = complainExtManager.selectByQuery(complainQuery);
        if(CollectionUtils.isEmpty(complainDOS)){
             return null;
        }
        List<ComplainDTO> complainDTOS = buildComplainDTO(complainDOS);
        if(CollectionUtils.isEmpty(complainDTOS)){
            return null;
        }
        result.setTotalCount(complainExtManager.countByQuery(complainQuery));
        result.setResult(complainDTOS);
        return result;
    }

    /**
     * 查询出所有的投诉数据
     * @return
     */
    private PageResult<ComplainDTO> queryComplainListNOParm(ComplainForm form){

        PageResult<ComplainDTO> result = new PageResult<>();
        result.setPageNo(form.getPageNo());
        result.setPageSize(form.getPageSize());

        ComplainQuery query = new ComplainQuery();
        query.setPageNo(form.getPageNo());
        query.setPageSize(form.getPageSize());
        query.createCriteria().andStatusEqualTo(RecordStatusEnum.VALID.getCode());

        List<ComplainDO> complainDOS = complainExtManager.selectByQuery(query);

        if(CollectionUtils.isEmpty(complainDOS)){
            return null;
        }
        List<ComplainDTO> complainDTOS = buildComplainDTO(complainDOS);
        if(CollectionUtils.isEmpty(complainDTOS)){
            return null;
        }
        result.setTotalCount(complainExtManager.countByQuery(query));
        result.setResult(complainDTOS);

        return result;
    }

    /**
     * 构建ComplainDTO
     * @param complainDOS
     * @return
     */
    private List<ComplainDTO> buildComplainDTO(List<ComplainDO> complainDOS){
        if(CollectionUtils.isEmpty(complainDOS)){
            return null;
        }
        List<ComplainDTO> complainDTOS = new ArrayList<>();
        complainDOS.forEach(complainDO -> {
            ComplainDTO complainDTO = null;
            UserDO userDO = userManager.selectByPrimaryKey(complainDO.getUserId());
            UserDO defendantUserDO  = userManager.selectByPrimaryKey(complainDO.getPersonByReportedId());
            DealComplainQuery dealComplainQuery = new DealComplainQuery();
            dealComplainQuery.createCriteria().andComplainIdEqualTo(complainDO.getComplainId())
                    .andStatusEqualTo(RecordStatusEnum.VALID.getCode());
            List<DealComplainDO> dealComplainDOS = dealComplainExtManager.selectByQuery(dealComplainQuery);
            if(!CollectionUtils.isEmpty(dealComplainDOS)){
                complainDTO  = buildComplainDTO(userDO,defendantUserDO,dealComplainDOS.get(0));
            }else{
                complainDTO = buildComplainDTO(userDO,defendantUserDO,null);
            }
            complainDTO.setComplainId(complainDO.getComplainId());
            complainDTO.setComplainDate(DateUtil.convertLocalDateTimeToString(complainDO.getGmtCreate()));
            complainDTO.setReason(complainDO.getReason());
            complainDTOS.add(complainDTO);
        });
        return complainDTOS;
    }

    /**
     * 查询投诉列表详情
     * @param complainId
     * @return
     */
    @Override
    public GeneralResult<ComplainDTO> queryComplainDetail(Long complainId) {
        //设置查询条件
        ComplainDO complainDO = complainManager.selectByPrimaryKey(complainId);
        if(Objects.isNull(complainDO)){
            log.error("查询投诉列表详情失败");
            return GeneralResult.error("","查询投诉列表详情失败");
        }
        UserDO userDO = userManager.selectByPrimaryKey(complainDO.getUserId());
        if(Objects.isNull(userDO)){
            log.error("查询投诉人信息失败");
            return GeneralResult.error("","查询投诉人信息失败");
        }
        UserDO defendantUserDO = userManager.selectByPrimaryKey(complainDO.getPersonByReportedId());
        if(Objects.isNull(defendantUserDO)){
            log.error("查询被投诉人信息失败");
            return GeneralResult.error("","查询被投诉人信息失败");
        }
        DealComplainDO dealComplainDO = dealComplainManager.selectByPrimaryKey(complainDO.getComplainId());
        if(Objects.isNull(defendantUserDO)){
            log.error("查询投诉列信息详情失败{}",dealComplainDO);
            return GeneralResult.error("","查询投诉列信息详情失败");
        }
        ComplainDTO complainDTO = buildComplainDTO(userDO,defendantUserDO,dealComplainDO);
        complainDTO.setComplainDate(complainDO.getGmtCreate().toString());
        complainDTO.setReason(complainDO.getReason());
        return GeneralResult.success(complainDTO);
    }

    /**
     * 查询投诉信息列表根据用户ID
     * @param userId 用户ID
     * @param complainStatus 0是投诉人 1是被投诉者
     * @return
     */
    @Override
    public GeneralResult<List<ComplainDTO>> queryComplainDetailBy(Long userId,Integer complainStatus) {
        ComplainQuery query = new ComplainQuery();
        ComplainQuery.Criteria criteria = query.createCriteria();
        if(ComplainStatusEnum.PLAINTIFF.getCode().equals(complainStatus)){
            criteria.andUserIdEqualTo(userId);
        }else{
            criteria.andPersonByReportedIdEqualTo(userId);
        }
        List<ComplainDO> complainDOS = complainExtManager.selectByQuery(query);
        if(CollectionUtils.isEmpty(complainDOS)){
            return GeneralResult.success(new ArrayList<>());
        }
        //获取用户的信息
        Map<Long,UserDO> userMap = queryUserDOMap(complainDOS);
        if(Objects.isNull(userMap)){
            return  GeneralResult.create("","查询用户信息失败");
        }
        //查询处理投诉详情
        List<Long> complainIds = complainDOS.stream().map(ComplainDO::getComplainId).collect(Collectors.toList());
        List<DealComplainDO> dealComplainDOS = queryComplainDealDetailBy(complainIds);
        Map<Long,DealComplainDO> dealComplainDOMap = dealComplainDOS.stream().collect(Collectors.toMap(DealComplainDO::getComplainId, Function.identity()));
        UserDO userDO = userManager.selectByPrimaryKey(userId);
        List<ComplainDTO> complainDTOS = new ArrayList<>();
        complainDOS.forEach(complainDO -> {
            Long complainId = complainDO.getComplainId();
            if(userMap.containsKey(complainId)){
                ComplainDTO complainDTO = buildComplainDTO(userDO,userMap.get(complainId),dealComplainDOMap.get(complainId));
                complainDTO.setComplainDate(DateUtil.convertLocalDateTimeToString(complainDO.getGmtCreate()));
                complainDTO.setReason(complainDO.getReason());
                complainDTOS.add(complainDTO);
            }
        });
        return GeneralResult.create(complainDTOS);
    }

    /**
     * 得到 key:complainId value:UserDO
     * @param complainDOS 投诉信息
     * @return Map
     */
    private Map<Long,UserDO> queryUserDOMap(List<ComplainDO> complainDOS){
        if(CollectionUtils.isEmpty(complainDOS)){
            return Collections.emptyMap();
        }
        //查询用户信息
        List<Long> userIds = complainDOS.stream().map(ComplainDO::getPersonByReportedId).collect(Collectors.toList());
        List<UserDO> userDOS = userService.queryUserInfoBy(userIds);
        if(CollectionUtils.isEmpty(userDOS)){
            return Collections.emptyMap();
        }
        //设置Map key:complainId value:userId
        Map<Long,Long> userIdMap = complainDOS.stream().collect(Collectors.toMap(ComplainDO::getComplainId,ComplainDO::getPersonByReportedId));
        //设置Map key:userId value:UserDO
        Map<Long,UserDO> userDOMap = userDOS.stream().collect(Collectors.toMap(UserDO::getUserId, Function.identity()));

        Map<Long,UserDO> userMap = new HashMap<>();
        complainDOS.forEach(complainDO -> {
            Long complainId = complainDO.getComplainId();
             if(userIdMap.containsKey(complainId) && userDOMap.containsKey(userIdMap.get(complainId))){
                 userMap.put(complainId,userDOMap.get(userIdMap.get(complainId)));
             }
        });
        return userMap;
    }

    /**
     * 拼接ComplainDTO
     * @param userDO
     * @param defendantUserDO
     * @param dealComplainDO
     * @return
     */
    private ComplainDTO buildComplainDTO(UserDO userDO,UserDO defendantUserDO,DealComplainDO dealComplainDO){
        ComplainDTO complainDTO = new ComplainDTO();
        if(!Objects.isNull(dealComplainDO)){
            complainDTO.setComplainId(dealComplainDO.getComplainId());
            complainDTO.setComplainRankDesc(ComplainRankEnum.getMsgByCode(dealComplainDO.getComplainRank()));
            complainDTO.setComplainRank(dealComplainDO.getComplainRank());
            complainDTO.setOperatorType(dealComplainDO.getOperatorType());
        }else{
            complainDTO.setComplainRankDesc("暂无处理");
        }
        complainDTO.setUserName(userDO.getUserName());
        complainDTO.setUserNo(userDO.getUserNo());
        complainDTO.setUserTel(userDO.getUserTel());
        complainDTO.setDefendantUserName(defendantUserDO.getUserName());
        complainDTO.setDefendantUserNo(defendantUserDO.getUserNo());
        complainDTO.setDefendantUserTel(defendantUserDO.getUserTel());
        return complainDTO;
    }

    /**
     * 处理投诉等级 只进行处理
     * @param form
     * @return
     */
    @Override
    public GeneralResult<Boolean> dealComplainRank(DealComplainForm form) {
        if(Objects.isNull(form)){
            log.error("处理投诉等级时入参出错");
            return GeneralResult.error(ErrorCodeEnum.NO_DATA.getErrorCode(),"处理投诉等级时入参出错");
        }
        //设置需要修改的条件
        DealComplainDO dealComplainDO = new DealComplainDO();
        dealComplainDO.setOperatorType(form.getOperatorType());
        dealComplainDO.setOperatorId(0L);
        dealComplainDO.setOperatorName("陶峰");
        dealComplainDO.setOperatorType(form.getOperatorType());
        //设置条件
        DealComplainQuery query = new DealComplainQuery();
        query.createCriteria().andComplainIdEqualTo(form.getComplainId())
                .andStatusEqualTo(RecordStatusEnum.VALID.getCode());

        Integer result = dealComplainExtManager.updateByQuerySelective(dealComplainDO,query);
        if(result>=1){
            if(!form.getOperatorType().equals(0)){
                DeductIntergityValuesEvent event = new DeductIntergityValuesEvent(form.getComplainId(),ComplainGradeEnum.getMsgByCode(ComplainRankEnum.valueOf(form.getOperatorType())));
                eventBus.post(event);
            }
            return GeneralResult.success(Boolean.TRUE);
        }
        return GeneralResult.create(Boolean.FALSE);
    }

    /**
     * 添加投诉详情
     * @param form
     * @return
     */
    @Override
    @Transactional
    public GeneralResult<Boolean> addComplain(AddComplainForm form) {
        if(Objects.isNull(form)){
            log.error("添加投诉详情时入参出错");
            return GeneralResult.error(ErrorCodeEnum.NO_DATA.getErrorCode(),"添加投诉详情时入参出错");
        }
        //设置需要修改的条件
        ComplainDO complainDO = new ComplainDO();
        Long userId = 0L;
        if(!Objects.isNull(form.getUserName())){
            userId  = userExtManager.selectUserIdByName("'"+form.getUserName()+"'");
        }
        Long defendantUserId = 0L;
        if(!Objects.isNull(form.getDefendantUserName())){
            defendantUserId = userExtManager.selectUserIdByName("'"+form.getDefendantUserName()+"'");
        }
        if(Objects.isNull(userId) || Objects.isNull(defendantUserId)){
             return GeneralResult.error("","你所输入的数据有误,不存在改用户");
        }
        complainDO.setUserId(userId);
        complainDO.setPersonByReportedId(defendantUserId);
        complainDO.setReason(form.getReason());
        //设置条件
        Integer result = complainManager.insertSelective(complainDO);
        if(result>=1){
            return GeneralResult.success(Boolean.TRUE);
        }
        return GeneralResult.create(Boolean.FALSE);
    }

    /**
     * 更新投诉
     * @param form
     * @return
     */
    @Override
    @Transactional
    public GeneralResult<Boolean> updateComplain(UpdateComplainForm form) {
        if(Objects.isNull(form)){
            log.error("更新投诉内容时入参出错");
            return GeneralResult.error(ErrorCodeEnum.NO_DATA.getErrorCode(),"更新投诉内容时入参出错");
        }
        ComplainDO complainDO = new ComplainDO();
        complainDO.setReason(form.getReason());

        ComplainQuery complainQuery = new ComplainQuery();
        complainQuery.createCriteria().andComplainIdEqualTo(form.getComplainId())
                .andStatusEqualTo(RecordStatusEnum.VALID.getCode());

        complainExtManager.updateByQuerySelective(complainDO,complainQuery);

        //设置需要修改的条件
        DealComplainDO dealComplainDO = new DealComplainDO();
        dealComplainDO.setOperatorId(0L);
        dealComplainDO.setOperatorName("陶峰");
        dealComplainDO.setComplainRank(form.getComplainRank());
        //设置条件
        DealComplainQuery query = new DealComplainQuery();
        query.createCriteria().andComplainIdEqualTo(form.getComplainId())
                .andStatusEqualTo(RecordStatusEnum.VALID.getCode());

        List<DealComplainDO> dealComplainDOS =  dealComplainExtManager.selectByQuery(query);
        Integer result = null;
        if(!CollectionUtils.isEmpty(dealComplainDOS) && dealComplainDOS.size()>=1){
           result = dealComplainExtManager.updateByQuerySelective(dealComplainDO,query);
        }else{
            dealComplainDO.setComplainId(form.getComplainId());
            dealComplainDO.setOperatorType(DealComplainEnum.PASS.getCode());
            result = dealComplainManager.insertSelective(dealComplainDO);
        }
        if(result>=1 ){
            return GeneralResult.success(Boolean.TRUE);
        }

        return GeneralResult.create(Boolean.FALSE,Boolean.FALSE);
    }


    /**
     * 根据投诉IDS查询投诉处理详情
     * @param complainIds 投诉IDS
     * @return 投诉处理详情
     */
    @Override
    public List<DealComplainDO> queryComplainDealDetailBy(List<Long> complainIds) {
        if(CollectionUtils.isEmpty(complainIds)){
            return Collections.emptyList();
        }
        DealComplainQuery complainQuery = new DealComplainQuery();
        complainQuery.createCriteria().andComplainIdIn(complainIds)
                .andStatusEqualTo(RecordStatusEnum.VALID.getCode());
        return dealComplainExtManager.selectByQuery(complainQuery);
    }

    /**
     * 根据投诉者id 查询用户id
     * @param complainId 投诉ID
     * @return
     */
    @Override
    public Long queryUserIdBy(Long complainId) {
        ComplainDO complainDO = complainManager.selectByPrimaryKey(complainId);
        if(Objects.isNull(complainDO)){
            return  null;
        }
        return complainDO.getPersonByReportedId();
    }
}
