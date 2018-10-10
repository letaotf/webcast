package com.taofeng.webcast.controller;

import com.taofeng.webcast.common.DTO.AssetManagementDTO;
import com.taofeng.webcast.common.DTO.AssetManagementDetailDTO;
import com.taofeng.webcast.common.entity.GeneralResult;
import com.taofeng.webcast.common.entity.PageResult;
import com.taofeng.webcast.common.form.AssetDetailsForm;
import com.taofeng.webcast.common.form.AssetManagementForm;
import com.taofeng.webcast.service.IAssetManagementService;
import com.taofeng.webcast.service.IExportExtraFeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * <p>资产管理列表控制</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/3/7 下午9:53
 * @since V1.0
 */
@RestController
@Api(tags = "资产管理【后台】",description = "资产管理")
@RequestMapping("/assetManagement")
@Slf4j
public class AssetManagementController {

    @Autowired
    private IAssetManagementService assetManagermentService;
    @Autowired
    private IExportExtraFeeService exportExtraFeeService;

    @ApiOperation(value = "查询资产列表")
    @GetMapping(value = "/queryAssetList.json")
    public GeneralResult<PageResult<AssetManagementDTO>> queryAssetList(@ModelAttribute AssetManagementForm form){
        return assetManagermentService.queryAssetList(form);
    }

    @ApiOperation(value = "查询资产详情")
    @GetMapping("/queryAssetListDetails.json")
    public GeneralResult<PageResult<AssetManagementDetailDTO>> queryAssetListDetails(@Valid @ModelAttribute  AssetDetailsForm form){
        return assetManagermentService.queryAssetListDetails(form);
    }

    @ApiOperation(value = "查询某用户的资产详情")
    @GetMapping("/queryAssetDetailBy.json")
    public GeneralResult<List<AssetManagementDetailDTO>> queryAssetDetailBy(Long userId){
        return assetManagermentService.queryAssetDetailBy(userId);
    }

    @ApiOperation(value = "导出费用信息", notes = "导出费用信息")
    @GetMapping("/exportALLFeeDetail.json")
    public GeneralResult exportALLFeeDetail(HttpServletRequest request, HttpServletResponse response, @Valid AssetDetailsForm form){
        //生成Excel
        String filename = exportExtraFeeService.getFileName();
        XSSFWorkbook workbook;
        try {
            workbook = exportExtraFeeService.exportALLFeeDetail(form);
        }catch (Exception e){
            return GeneralResult.create(e.getMessage());
        }
        log.info("费用明细开始下载");
        OutputStream out = null;
        String result;
        try {
            //输出流
            out = response.getOutputStream();
            //获取请求头名称（获取浏览器名称）
            String userAgent = request.getHeader("USER-AGENT");
            String path;
            //对浏览器进行区分
            if (StringUtils.contains(userAgent, "Firefox") || StringUtils.contains(userAgent, "firefox")) {
                path = new String(filename.getBytes(), StandardCharsets.ISO_8859_1.name());
            }else{
                path = URLEncoder.encode(filename,"UTF-8");
            }
            String headStr = "attachment; filename=\"" + path + "\"";
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Content-Disposition", headStr);
            workbook.write(out);
            out.flush();
            result = "下载成功";
        } catch (Exception e){
            result = "下载失败";
        } finally {
            if( null != out){
                try {
                    out.close();
                } catch (IOException e) {
                    log.info("输出流关闭失败");
                }
            }
        }
        log.info("费用明细下载结束");
        return GeneralResult.success(result);
    }



}
