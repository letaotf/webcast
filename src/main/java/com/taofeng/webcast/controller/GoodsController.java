package com.taofeng.webcast.controller;

import com.taofeng.webcast.common.DTO.BuyGoodsRecordDTO;
import com.taofeng.webcast.common.DTO.GoodsDTO;
import com.taofeng.webcast.common.entity.GeneralResult;
import com.taofeng.webcast.common.entity.PageResult;
import com.taofeng.webcast.common.form.*;
import com.taofeng.webcast.service.IGoodsManagementService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>商品管理</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/3/7 下午10:05
 * @since V1.0
 */
@RestController
@Api(tags = "商品管理[后台]",description = "商品管理")
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private IGoodsManagementService goodsManagementService;

    @GetMapping("/queryOnlineListGoods.json")
    @ApiOperation(value = "查询在架上的商品")
    public GeneralResult<PageResult<GoodsDTO>> queryOnlineListGoods(@ModelAttribute GoodsForm form){
        return goodsManagementService.queryOnlineListGoods(form);
    }

    @GetMapping("/queryAllListGoods.json")
    @ApiOperation(value = "查询全部的商品")
    public GeneralResult<PageResult<GoodsDTO>> queryAllListGoods(@ModelAttribute GoodsForm form){
        return goodsManagementService.queryAllListGoods(form);
    }

    @PostMapping("/addGoods.json")
    @ApiOperation(value = "添加商品")
    public GeneralResult<Boolean> addGoods(@Valid @RequestBody AddGoodForm form){
        return goodsManagementService.addGoods(form);
    }

    @PostMapping("/updateGoods.json")
    @ApiOperation(value = "更新商品")
    public GeneralResult<Boolean> updateGoods(@Valid @RequestBody UpdateGoodsForm form){
        return goodsManagementService.updateGoods(form);
    }

    @PostMapping("/deleteGoods.json")
    @ApiOperation(value = "删除商品")
    public GeneralResult<Boolean> deleteGoods(@Valid @RequestBody DeleteGoodsForm form){
        return goodsManagementService.deleteGoods(form);
    }

    @PostMapping("/dealGoodsOnlineStatus.json")
    @ApiOperation(value = "处理商品的在线状态")
    public GeneralResult<Boolean> dealGoodsOnlineStatus(@Valid @RequestBody GoodsOnlineForm form){
        return goodsManagementService.dealGoodsOnlineStatus(form);
    }

    @GetMapping("/queryBuyGoodsRecord.json")
    @ApiOperation(value = "查询用户商品的购买记录")
    public GeneralResult<List<BuyGoodsRecordDTO>> queryBuyGoodsRecord(Long userId){
        return goodsManagementService.queryBuyGoodsRecord(userId);
    }

    @PostMapping("/addBuyGoods.json")
    @ApiOperation(value = "添加商品")
    public GeneralResult<Boolean> addBuyGoods(@Valid @RequestBody BuyGoodsForm form){
        return goodsManagementService.addBuyGoods(form);
    }
}
