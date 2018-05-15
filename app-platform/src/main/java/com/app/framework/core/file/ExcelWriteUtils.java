package com.app.framework.core.file;

import com.app.framework.core.utils.Log;
import com.app.framework.core.utils.LoggerFactory;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by yangyijun on 2018/5/14.
 */
public final class ExcelWriteUtils {
    private static final Log logger = LoggerFactory.getLogger(ExcelWriteUtils.class);
    private static final String EXCEL_XLS = ".xls";
    private static final String EXCEL_XLSX = ".xlsx";

    public static void writeExcel(String sheetName, String fileName, String[] titles, List<Map<String, Object>> datas) {
        OutputStream fos = null;
        Workbook workbook = null;
        try {
            //第一步，创建一个workbook对应一个excel文件
            if (fileName.endsWith(EXCEL_XLS)) {
                workbook = new HSSFWorkbook();
            } else if (fileName.endsWith(EXCEL_XLSX)) {
                workbook = new XSSFWorkbook();
            }
            //第二步，在workbook中创建一个sheet对应excel中的sheet
            Sheet sheet = workbook.createSheet(sheetName);
            //第三步，在sheet表中添加表头第0行，老版本的poi对sheet的行列有限制
            Row header = sheet.createRow(0);
            //第四步，创建单元格，设置表头
            for (int i = 0; i < titles.length; i++) {
                header.createCell(i).setCellValue(titles[i]);
            }
            //第五步，写入数据
            for (int i = 0; i < datas.size(); i++) {
                Row body = sheet.createRow(i + 1);
                Map<String, Object> rowData = datas.get(i);
                int j = 0;
                for (Map.Entry<String, Object> data : rowData.entrySet()) {
                    body.createCell(j).setCellValue(data.getValue().toString());
                    j++;
                }
            }
            //将文件保存到指定的位置
            fos = new FileOutputStream(fileName);
            workbook.write(fos);
        } catch (IOException e) {
            logger.error("写入excel异常", e);
        } finally {
            try {
                workbook.close();
            } catch (IOException e) {
                logger.error("关闭workbook异常", e);
            }
            try {
                fos.close();
            } catch (IOException e) {
                logger.error("关闭文件输出流异常", e);
            }
        }
    }

    public static void main(String[] args) {
        List<Map<String, Object>> datas = new LinkedList<>();
        Map<String, Object> map1 = new LinkedHashMap<>();
        map1.put("name", "yangyijun");
        map1.put("age", "122");
        datas.add(map1);

        writeExcel("test1", "test1.xlsx", new String[]{"name", "age"}, datas);
    }
}
