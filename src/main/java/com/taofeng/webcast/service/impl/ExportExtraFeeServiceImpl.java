package com.taofeng.webcast.service.impl;

import com.taofeng.webcast.common.DTO.AssetManagementDetailDTO;
import com.taofeng.webcast.common.entity.GeneralResult;
import com.taofeng.webcast.common.entity.PageResult;
import com.taofeng.webcast.common.form.AssetDetailsForm;
import com.taofeng.webcast.service.IAssetManagementService;
import com.taofeng.webcast.service.IExportExtraFeeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * <p>导出费用列表</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/1/29 下午3:05
 * @since V1.0
 */
@Service
@Slf4j
public class ExportExtraFeeServiceImpl implements IExportExtraFeeService {

    @Autowired
    private IAssetManagementService assetManagementService;

    /**
     * 导出费用列表
     *
     * @param form
     */
    @Override
    public XSSFWorkbook exportALLFeeDetail(AssetDetailsForm form){
        //创建表头
        XSSFWorkbook workbook = new XSSFWorkbook();
        //创建页
        Sheet sheet = workbook.createSheet();
        //表头数据标题
        String titleName[] = {"年", "月", "日", "鱼丸总价", "鲜花总价", "火箭总价","总价"};
        //在首行创建数据列表
        createFirstRow(sheet, titleName);
        //插入数据
        dealPageFeeDetail(sheet, form);
        //设置单元格样式
        setColumnStyle(workbook);
        //保存excel
        saveExcel(workbook,getFileName());
        return workbook;
    }

    @Override
    public String getFileName() {
        //文件名称
        StringBuilder path = new StringBuilder();
        LocalDateTime dateTime = LocalDateTime.now();
        path.append("资产明细-");
        path.append(dateTime.format(DateTimeFormatter.ofPattern("yyyyMMddHHmm")));
        path.append(".xls");
        return path.toString();
    }

    /**
     * 保存工作薄
     *
     * @param wb
     */
    private void saveExcel(XSSFWorkbook wb, String path) {
        FileOutputStream fileOut = null;
        try {
            fileOut = new FileOutputStream("/Users/letao/" + path);
            wb.write(fileOut);
            fileOut.close();
        } catch (FileNotFoundException e) {
            log.info("没有发现文件", e);
        } catch (IOException e) {
            log.info("文件IO异常", e);
        }
    }

    /**
     * 处理分页数据
     *
     * @param sheet
     * @param form
     */
    private void dealPageFeeDetail(Sheet sheet, AssetDetailsForm form) {
        //查询导数据
        PageResult<AssetManagementDetailDTO> result = queryFeeList(form);
        int totalPage = result.getTotalPage();
        int p = 1;
        do {
            form.setPageNo(p);
            if (p > 1) {
                result = queryFeeList(form);
            }
            if (CollectionUtils.isEmpty(result.getResult())) {

            }
            setFeeDetail(sheet, result.getResult());
            p = p + 1;
        } while (p <= totalPage);
    }

    /**
     * 获取查询出来的fee List
     * @param form
     * @return
     */
    private PageResult<AssetManagementDetailDTO> queryFeeList(AssetDetailsForm form) {
        GeneralResult<PageResult<AssetManagementDetailDTO>> result = assetManagementService.queryAssetListDetails(form);
        if (result == null || null == result.getData()) {
            return new PageResult<>();
        }
        return result.getData();
    }


    /**
     * 创建首行数据列
     *
     * @param sheet
     * @param nameCell
     */
    private void createFirstRow(Sheet sheet, String[] nameCell) {
        //第一行,列表标题
        Row row = sheet.createRow(0);

        if (nameCell != null) {
            for (int i = 0; i < nameCell.length; i++) {
                Cell cell = row.createCell(i);
                //加入列表名称
                cell.setCellValue(nameCell[i]);
            }
        }
    }

    /**
     * 创建row 对象
     *
     * @param sheet
     * @return
     */
    private Row createRow(Sheet sheet) {
        if (sheet == null) {
            return null;
        }
        Integer rowNum = sheet.getLastRowNum();
        return sheet.createRow(rowNum + 1);
    }

    /**
     * 创建 cell 对象
     *
     * @param row
     * @return
     */
    private Cell createCell(Row row) {
        if (row == null) {
            return null;
        }
        Cell cell = null;
        Integer columnNum = row.getPhysicalNumberOfCells();
        if (columnNum != null) {
            cell = row.createCell(columnNum);
        }
        return cell;
    }

    /**
     * 插入费用数据
     *
     * @param sheet
     * @param chargeVOList
     */
    private void setFeeDetail(Sheet sheet, List<AssetManagementDetailDTO> chargeVOList){
        if (CollectionUtils.isEmpty(chargeVOList)) {

        }
        //费用明细
        chargeVOList.forEach(chargeVO -> {
            //创建行
            Row row = createRow(sheet);
            if (null == row) {
                log.error("创建行失败");
            }
            //年
            setFeeMessage(row, chargeVO.getYearDescription());
            //月
            setFeeMessage(row, chargeVO.getMonthDescription());
            //日
            setFeeMessage(row, chargeVO.getDayDescription());
            //鱼丸总价
            setFeeMessage(row, chargeVO.getFishBallMoney());
            //鲜花总价
            setFeeMessage(row, chargeVO.getFlowerMoney());
            //火箭总价
            setFeeMessage(row, chargeVO.getRocketMoney());
            //总价
            setFeeMessage(row, chargeVO.getAllMoney());

        });
    }

    /**
     * 插入费用列表中获取的其他信息
     *
     * @param row
     * @param detail
     */
    private <T> void setFeeMessage(Row row, T detail) {
        if (row != null) {
            Cell cell = createCell(row);
            if (null == cell) {
                return;
            }
            if (detail != null) {
                if (detail instanceof String) {
                    cell.setCellValue((String) detail);
                } else if (detail instanceof Double && !detail.equals(0.0)) {
                    cell.setCellValue((Double)detail);
                } else if (detail instanceof Long && !detail.equals(0)) {
                    cell.setCellValue((Long)detail);
                }
            } else {
                cell.setCellValue("");
            }
        }
    }

    /**
     * 设置单元格风格
     *
     * @param workbook
     */
    private void setColumnStyle(XSSFWorkbook workbook) {
        Sheet sheet = workbook.getSheetAt(0);
        //设置单元格的宽高
        for (int i = 0; i <= sheet.getLastRowNum(); i++) {
            //设置首行的样式
            Row row = sheet.getRow(i);
            if (null != row) {
                for (int j = 0; j < row.getPhysicalNumberOfCells(); j++) {
                    if (j <= 4 || j == 13 || j == 23 || j == 24) {
                        sheet.setColumnWidth(j, 22 * 256);
                    } else {
                        sheet.setColumnWidth(j, 15 * 256);
                    }
                }
            }
        }
    }

    /**
     * 单元格风格标题的风格
     *
     * @param workbook
     * @param fontSize
     * @return
     */
    private XSSFCellStyle getColumnTopStyle(XSSFWorkbook workbook, int fontSize) {
        // 设置字体
        XSSFFont font = workbook.createFont();
        //设置字体大小
        font.setFontHeightInPoints((short) fontSize);
        //字体加粗
        font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
        //设置字体名字
        font.setFontName("宋体");
        //设置样式;
        XSSFCellStyle style = workbook.createCellStyle();
        //在样式用应用设置的字体;
        style.setFont(font);
        //设置自动换行;
        style.setWrapText(false);     //设置水平对齐的样式为居中对齐;
        style.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        //设置垂直对齐的样式为居中对齐;
        style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
        return style;
    }

    /**
     * 单元格风格标题的风格
     *
     * @param workbook
     * @return
     */
    private XSSFCellStyle getColumnTopStyle(XSSFWorkbook workbook) {
        //设置样式;
        XSSFCellStyle style = workbook.createCellStyle();
        //设置自动换行;
        style.setWrapText(false);     //设置水平对齐的样式为居中对齐;
        style.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        //设置垂直对齐的样式为居中对齐;
        style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
        return style;
    }

}
