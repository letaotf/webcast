package com.taofeng.webcast.controller;

import com.taofeng.webcast.common.DTO.ComplainDTO;
import com.taofeng.webcast.common.entity.GeneralResult;
import com.taofeng.webcast.common.entity.PageResult;
import com.taofeng.webcast.common.form.AddComplainForm;
import com.taofeng.webcast.common.form.ComplainForm;
import com.taofeng.webcast.common.form.DealComplainForm;
import com.taofeng.webcast.common.form.UpdateComplainForm;
import com.taofeng.webcast.service.IComplainManagementService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>投诉管理</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/3/7 下午10:29
 * @since V1.0
 */
@RestController
@Api(tags = "投诉管理[后台]",description = "投诉管理")
@RequestMapping("/complain")
public class ComplainController {

    @Autowired
    private IComplainManagementService complainManagementService;

    @ApiOperation(value = "查询投诉列表")
    @GetMapping("/queryComplainList.json")
    public GeneralResult<PageResult<ComplainDTO>> queryComplainList(ComplainForm form){
        return complainManagementService.queryComplainList(form);
    }

    @ApiOperation(value = "查询投诉列表详情")
    @GetMapping("/queryComplainDetail.json")
    public GeneralResult<ComplainDTO> queryComplainDetail(Long complainId){
        return complainManagementService.queryComplainDetail(complainId);
    }

    @ApiOperation(value = "处理投诉等级")
    @PostMapping("/dealComplainRank.json")
    public GeneralResult<Boolean> dealComplainRank(@Valid @RequestBody DealComplainForm form){
        return complainManagementService.dealComplainRank(form);
    }

    @ApiOperation(value = "添加投诉")
    @PostMapping("/addComplain.json")
    public GeneralResult<Boolean> addComplain(@Valid @RequestBody AddComplainForm form){
        return complainManagementService.addComplain(form);
    }

    @ApiOperation(value = "更新投诉")
    @PostMapping("/updateComplain.json")
    public GeneralResult<Boolean> updateComplain(@Valid @RequestBody UpdateComplainForm form){
        return complainManagementService.updateComplain(form);
    }

    @ApiOperation(value = "查询某一个用户的详细信息")
    @GetMapping("/queryComplainDetailBy.json")
    GeneralResult<List<ComplainDTO>> queryComplainDetailBy(Long userId, Integer complainStatus){
        return complainManagementService.queryComplainDetailBy(userId,complainStatus);
    }
}
