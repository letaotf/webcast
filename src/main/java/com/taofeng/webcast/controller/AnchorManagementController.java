package com.taofeng.webcast.controller;

import com.taofeng.webcast.common.DTO.AnchorDTO;
import com.taofeng.webcast.common.entity.GeneralResult;
import com.taofeng.webcast.common.entity.PageResult;
import com.taofeng.webcast.common.form.AnchorForm;
import com.taofeng.webcast.common.form.AuditAnchorForm;
import com.taofeng.webcast.service.IAnchorManagementService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>网络主播的管理</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/3/12 下午3:43
 * @since V1.0
 */
@RestController
@Api(tags = "网络主播的管理[后台]",description = "网络主播的管理")
@RequestMapping("/anchor")
public class AnchorManagementController {

    @Autowired
    private IAnchorManagementService anchorManagement;

    @ApiOperation(value = "查询网络主播的申请记录")
    @GetMapping("/queryAnchorApplyRecord.json")
    public GeneralResult<PageResult<AnchorDTO>> queryAnchorApplyRecord(AnchorForm form){
         return anchorManagement.queryAnchorApplyRecord(form);
    }

    @ApiOperation(value = "审核网络主播")
    @PostMapping(value = "/auditAnchor.json")
    public GeneralResult<Boolean> auditAnchor(@RequestBody AuditAnchorForm form){
        return anchorManagement.auditAnchor(form);
    }

}
