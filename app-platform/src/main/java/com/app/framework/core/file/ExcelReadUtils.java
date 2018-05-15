package com.app.framework.core.file;

/**
 * Created by Administrator on 2018/4/21 0021.
 */

import com.app.framework.core.utils.Log;
import com.app.framework.core.utils.LoggerFactory;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import org.apache.commons.lang.StringUtils;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class ExcelReadUtils {
    private static final Log logger = LoggerFactory.getLogger(ExcelReadUtils.class);

    public static void main(String[] args) {
        File file = new File("readexcel.xls");
        try {
            List list = ExcelReadUtils.readExcel(file);
            System.out.println(list.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 去读Excel的方法readExcel，该方法的入口参数为filePath
    public static List<List> readExcel(String filePath) {
        if (StringUtils.isBlank(filePath)) {
            throw new RuntimeException("文件名称不能为空");
        }
        File file = new File(filePath);
        if (file.exists()) {
            return readExcel(file);
        } else {
            throw new RuntimeException("目标文件不存在");
        }

    }

    // 去读Excel的方法readExcel，该方法的入口参数为一个File对象
    public static List<List> readExcel(File file) {
        String absolutePath = null;
        InputStream is = null;
        Workbook wb = null;
        List<List> sheetList = new LinkedList<>();
        try {
            absolutePath = file.getAbsolutePath();
            // 创建输入流，读取Excel
            is = new FileInputStream(absolutePath);
            // jxl提供的Workbook类
            wb = Workbook.getWorkbook(is);
            // Excel的页签数量
            Sheet[] sheets = wb.getSheets();
            //所有的sheet全部存放到这个list中
            for (Sheet sheet : sheets) {
                //每个sheet存放在这个list中
                List<List> rowList = new LinkedList<>();
                // 每个页签创建一个Sheet对象
                int rows = sheet.getRows();
                int columns = sheet.getColumns();

                for (int i = 0; i < rows; i++) {
                    List columnList = new LinkedList();
                    for (int j = 0; j < columns; j++) {
                        String cellinfo = sheet.getCell(j, i).getContents();
                        columnList.add(cellinfo);
                    }
                    rowList.add(columnList);
                }
                sheetList.add(rowList);
            }
        } catch (FileNotFoundException e) {
            logger.error("没有找到文件【{}】", e, absolutePath);
        } catch (BiffException e) {
            logger.error("读取文件【{}】时异常", e, absolutePath);
        } catch (IOException e) {
            logger.error("读取文件【{}】时异常", e, absolutePath);
        } finally {
            try {
                if (null != is)
                    is.close();
            } catch (IOException e) {
                logger.error("关闭文件【{}】流时异常", e, absolutePath);
            }
            if (null != wb) {
                try {
                    wb.close();
                } catch (Exception e) {
                    logger.error("关闭文件【{}】流时异常", e, absolutePath);
                }
            }
        }
        return sheetList;
    }
}
