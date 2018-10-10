package com.taofeng.webcast.controller;

import com.taofeng.webcast.common.DTO.IntegerRecordDTO;
import com.taofeng.webcast.common.DTO.IntergitValuesDTO;
import com.taofeng.webcast.common.entity.GeneralResult;
import com.taofeng.webcast.common.entity.PageResult;
import com.taofeng.webcast.common.form.IntergitValuesForm;
import com.taofeng.webcast.common.form.UpdateIntergitValuesForm;
import com.taofeng.webcast.service.IntergitValuesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * <p>诚信值管理</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/3/8 下午1:58
 * @since V1.0
 */
@RestController
@Api(tags = "诚信值管理[后台]",description = "诚信值管理")
@RequestMapping("/intergitValues")
@Slf4j
public class IntergitValuesController {

    @Autowired
    private IntergitValuesService intergitValuesService;

    @ApiOperation(value = "查询诚信值的列表")
    @GetMapping("/queryIntergitValuesList.json")
    public GeneralResult<PageResult<IntergitValuesDTO>> queryIntergitValuesList(@ModelAttribute IntergitValuesForm form){
        return intergitValuesService.queryIntergitValuesList(form);
    }

    @ApiOperation(value = "修改诚信值")
    @PostMapping("/updateIntergitValues.json")
    public GeneralResult<Boolean> updateIntergitValues(@Valid @RequestBody UpdateIntergitValuesForm form){
        return intergitValuesService.updateIntergitValues(form);
    }

    @ApiOperation(value = "查询诚信值记录")
    @GetMapping("/queryIntergitValuesRecord.json")
    GeneralResult<IntegerRecordDTO> queryIntergitValuesRecord(Long userId){
        return intergitValuesService.queryIntergitValuesRecord(userId);
    }
}
