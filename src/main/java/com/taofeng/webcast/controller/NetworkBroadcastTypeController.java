package com.taofeng.webcast.controller;

import com.taofeng.webcast.common.DTO.NetworkBroadcastDTO;
import com.taofeng.webcast.common.entity.GeneralResult;
import com.taofeng.webcast.common.entity.PageResult;
import com.taofeng.webcast.common.form.AddNetworkBroadcastForm;
import com.taofeng.webcast.common.form.DeleteNetworkBroadcastForm;
import com.taofeng.webcast.common.form.NetworkBroadcastForm;
import com.taofeng.webcast.common.form.UpdateNetworkBroadcastForm;
import com.taofeng.webcast.service.INetworkBroadcastTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * <p>网络节目管理</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/3/13 下午2:06
 * @since V1.0
 */
@RestController
@RequestMapping("/networkBroadcastType")
@Api(tags = "网络节目管理[后台]",description = "网络节目管理")
public class NetworkBroadcastTypeController {

    @Autowired
    private INetworkBroadcastTypeService networkBroadcastTypeService;

    @ApiOperation(value = "查询节目清单")
    @GetMapping("/queryNetworkBroadcastType.json")
    public GeneralResult<PageResult<NetworkBroadcastDTO>> queryNetworkBroadcastType(@ModelAttribute NetworkBroadcastForm form){
        return networkBroadcastTypeService.queryNetworkBroadcastType(form);
    }

    @ApiOperation(value = "查询节目清单详情")
    @GetMapping("/queryNetworkBroadcastTypeDetail.json")
    public GeneralResult<NetworkBroadcastDTO> queryNetworkBroadcastTypeDetail(Long networkBroadcastTypeDetailId){
       return networkBroadcastTypeService.queryNetworkBroadcastTypeDetail(networkBroadcastTypeDetailId);
    }

    @ApiOperation(value = "添加节目")
    @PostMapping("/addNetworkBroadcastType.json")
    public GeneralResult<Boolean> addNetworkBroadcastType(@Valid @RequestBody AddNetworkBroadcastForm form){
        return networkBroadcastTypeService.addNetworkBroadcastType(form);
    }

    @ApiOperation(value = "更新节目")
    @PostMapping("/updateNetworkBroadcastType.json")
    public GeneralResult<Boolean> updateNetworkBroadcastType(@Valid @RequestBody UpdateNetworkBroadcastForm form){
        return networkBroadcastTypeService.updateNetworkBroadcastType(form);
    }

    @ApiOperation(value = "删除节目")
    @PostMapping("/deleteNetworkBroadcastType.json")
    public GeneralResult<Boolean> deleteNetworkBroadcastType(@Valid @RequestBody DeleteNetworkBroadcastForm form){
        return networkBroadcastTypeService.deleteNetworkBroadcastType(form);
    }
}
