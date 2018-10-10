package com.taofeng.webcast.controller;

import com.taofeng.webcast.common.DTO.NoticeDTO;
import com.taofeng.webcast.common.entity.GeneralResult;
import com.taofeng.webcast.common.form.NoticeForm;
import com.taofeng.webcast.service.INoticeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>公告管理</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/3/13 下午1:53
 * @since V1.0
 */
@RestController
@RequestMapping("/notice")
@Api(tags = "公告管理[后台]",description = "公告管理")
public class NoticeController {

    @Autowired
    private INoticeService noticeService;

    @ApiOperation(value = "添加公告")
    @PostMapping("/addNotice.json")
    public GeneralResult<Boolean> addNotice(@Valid @RequestBody NoticeForm form){
       return noticeService.addNotice(form);
    }

    @ApiOperation(value = "查询公告信息")
    @GetMapping("/queryNoticeInfo.json")
    public GeneralResult<List<NoticeDTO>> queryNoticeInfo(){
        return noticeService.queryNoticeInfo();
    }

 }
