package com.taofeng.webcast.service;

import com.taofeng.webcast.common.form.AssetDetailsForm;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * <p>导出费用列表</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/1/29 下午3:04
 * @since V1.0
 */
public interface IExportExtraFeeService {

    /**
     * 导出费用列表
     * @param form
     */
    XSSFWorkbook exportALLFeeDetail(AssetDetailsForm form);

    /**
     * 文件名字
     * @return
     */
    String getFileName();

}
