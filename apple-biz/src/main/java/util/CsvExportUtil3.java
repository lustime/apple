/*
 * Copyright (C), 2002-2018, 苏宁易购电子商务有限公司
 * FileName: CsvExportUtil3.java
 * Author:   17040407
 * Date:     18-1-4 上午11:40
 * Description: 智能风控系统
 * History: //修改记录
 * <author> <time> <version> <desc>
 * 修改人姓名      修改时间      版本号        描述
 */

package util;

import com.google.common.collect.Lists;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 *
 * @param <T> the type parameter
 * @author 17040407
 * @see [相关类/方法]（可选）
 * @since [产品 /模块版本] （可选）
 */
public class CsvExportUtil3<T> {

    private static final Logger LOGGER = LoggerFactory.getLogger(CsvExportUtil3.class);
    private static final String LINE_SEPARATOR = "\n";

    /**
     * 标题
     */
    List<String> exportFieldTitle = new ArrayList<String>();
    /**
     * 导出的字段的get方法
     */
    List<Method> methodList = new ArrayList<Method>();


    /**
     * Export.
     *
     * @param response     the response
     * @param destFileName the dest file name
     * @param tempFileName the temp file name
     * @param data         the data
     */
    public void export(HttpServletResponse response, String destFileName, String tempFileName, Collection<T> data){
        response.setHeader("Content-Disposition", "attachment;filename=" + destFileName + ".csv");
        response.setContentType("text/csv");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            LOGGER.error("io异常", e);
        }
        csvWriter(out, tempFileName, data);
    }

    /**
     * 删除temp目录
     *
     * @param dir
     * @return
     */
    private static boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        // 目录此时为空，可以删除
        return dir.delete();
    }

    /**
     * 导出
     * @param out
     * @param tempFileName
     * @param data
     */
    private void csvWriter(PrintWriter out, String tempFileName, Collection<T> data) {
        BufferedReader br = null;
        CSVPrinter csvPrinter = null;
        InputStream in = null;
        CSVFormat csvFormat = CSVFormat.DEFAULT.withRecordSeparator(LINE_SEPARATOR);
        try {
            // 读入模板表头
            in = this.getClass().getResourceAsStream("/conf/exportCsv/" + tempFileName + ".csv");
            br = new BufferedReader(new InputStreamReader(in));
            String fieldLine = br.readLine();
            if(null == fieldLine){
                LOGGER.error("模板文件为空");
                return;
            }
            Collections.addAll(exportFieldTitle, fieldLine.split(","));

            csvPrinter = new CSVPrinter(out, csvFormat);
            csvPrinter.printRecord(exportFieldTitle.toArray());

            Iterator<T> iterator = data.iterator();

            /** 取得实际泛型 */
            T tObject = iterator.next();

            Class<T> clazz = (Class<T>) tObject.getClass();

            //组装methods
            for(String methodStr : exportFieldTitle){
                String getMethodName = "get" + methodStr.substring(0, 1).toUpperCase() + methodStr.substring(1);
                Method getMethod = clazz.getMethod(getMethodName, new Class[] {});
                methodList.add(getMethod);
            }

            for (T t : data){
                //TODO 反射赋值
                List<String> records = Lists.newArrayList();
                for (int k = 0; k < methodList.size(); k++) {
                    Method getMethod = methodList.get(k);
                    Object value = getMethod.invoke(t);
                    String textValue = getValue(value);// 值
                    records.add(textValue);
                }
                csvPrinter.printRecord(records);
            }
        } catch (Exception e) {
            LOGGER.error("异常", e);
        } finally {
            try {
                if (csvPrinter != null) {
                    csvPrinter.flush();
                    csvPrinter.close();
                }
                if(null != in){
                    in.close();
                }
                if(null != br){
                    br.close();
                }
            } catch (IOException e) {
                LOGGER.error("io异常", e);
            }
        }
    }

    /**
     * 基本类型的转换
     *
     * @param value 传入要转换的值
     * @return value value
     */
    private String getValue(Object value) {
        String textValue = "";
        if (value == null) {
            return textValue;
        }

        if (value instanceof String) {
            textValue = value.toString();
        } else if (value instanceof Date) {
            Date date = (Date) value;
            textValue = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
        } else {
            textValue = value.toString();
        }
        return textValue;
    }
}
