/*
 * Copyright (C), 2002-2018, 苏宁易购电子商务有限公司
 * FileName: CsvExport.java
 * Author:   17040407
 * Date:     18-1-4 上午11:40
 * Description: 智能风控系统
 * History: //修改记录
 * <author> <time> <version> <desc>
 * 修改人姓名      修改时间      版本号        描述
 */
package util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * CvsExport方法-csv导出.
 * 
 * @author 徐大伟
 * @param <T> tag泛型
 * @version 1.0
 */
public class CsvExport<T> {
    /** 记录日志 */
    private static Logger logger = LoggerFactory.getLogger(CsvExport.class);
    /** 统计数 */
    int rowCount = 0;
    /** 标题 */
    List<String> exportfieldtile = new ArrayList<String>();
    /** 导出的字段的get方法 */
    List<Method> methodObj = new ArrayList<Method>();

    /**
     * CvsExport方法-csv导出.
     * 
     * @param title 文件名
     * @param dataset 导出的数据集
     * @param out 输出流
     * @param dataFileds 标题
     */
    public void exportCsv(String title, Collection<T> dataset, PrintWriter out, Map<String, String> dataFileds) {
        this.exportCsv(title, dataset, out, null, dataFileds);
    }

    /**
     * 
     * 导出
     * 
     * @param title 文件名
     * @param dataset 导出的数据集
     * @param out 输出流
     * @param titles 标题
     * @param dataFileds 标题字段
     */
    public void exportCsv(String title, Collection<T> dataset, PrintWriter out, List<String[]> titles,
                          Map<String, String> dataFileds) {
        try {
            pd(title, dataset, out, titles, dataFileds);
            /** 首先检查数据看是否是正确的 */
            Iterator<T> iterator = dataset.iterator();
            if (iterator == null) {
                logger.error("没有数据可以导出！");
                return;
            }

            /** 取得实际泛型 */
            T tObject = iterator.next();

            @SuppressWarnings("unchecked")
            //去除强制类型转换的警告
                    Class<T> clazz = (Class<T>) tObject.getClass();

            /** 遍历整个filed */
            for (Map.Entry<String, String> m : dataFileds.entrySet()) {
                if (isNull(m.getKey()))
                    continue;
                /** 添加到标题 */
                exportfieldtile.add(m.getValue());
                /** 添加到需要导出的字段的方法 */
                String fieldname = m.getKey();
                String getMethodName = "get" + fieldname.substring(0, 1).toUpperCase() + fieldname.substring(1);
                Method getMethod = clazz.getMethod(getMethodName, new Class[] {});
                methodObj.add(getMethod);
            }

            export(out, dataset);
        } catch (NoSuchMethodException e) {
            logger.error("NoSuchMethodException:", e);
        }
    }

    /**
     * 导出数据
     * 
     * @param value 传入要转换的值
     * @return
     */

    public void export(PrintWriter out, Collection<T> dataset) {
        /** 产生cvs标题 */
        try {
            for (int i = 0; i < exportfieldtile.size(); i++) {
                String titletemp = exportfieldtile.get(i);
                out.print(titletemp);
                if (i < exportfieldtile.size() - 1) {
                    out.print(",");
                }
            }
            out.print("\r\n");

            /*** 循环整个集合 */
            Iterator<T> iterator = dataset.iterator();
            while (iterator.hasNext()) {
                /** 从第二行开始写，第一行是标题 */
                T t = (T) iterator.next();
                for (int k = 0; k < methodObj.size(); k++) {
                    Method getMethod = methodObj.get(k);
                    Object value = getMethod.invoke(t, new Object[] {});
                    String textValue = getValue(value) + "\t";// 值
                    out.write(textValue);
                    if (k < methodObj.size() - 1) {
                        out.print(",");
                    }
                }
                out.print("\r\n");
                out.flush();
            }
            /** 结束后输出 */
        } catch (SecurityException e) {
            logger.error("SecurityException:", e);
        } catch (IllegalAccessException e) {
            logger.error("IllegalAccessException:", e);
        } catch (IllegalArgumentException e) {
            logger.error("IllegalArgumentException:", e);
        } catch (InvocationTargetException e) {
            logger.error("InvocationTargetException:", e);
        }
    }

    /**
     * 基本判断
     * 
     * @param value 传入要转换的值
     * @return
     */

    public void pd(String title, Collection<T> dataset, PrintWriter out, List<String[]> titles,
                   Map<String, String> dataFileds) {
        if (CollectionUtils.isEmpty(dataset) || title == null || out == null) {
            return;
        }
        if (dataFileds == null || dataFileds.size() <= 0) {
            logger.error("要导出的字段信息不能为空!");
        }
            rowCount = titles.size();
    }

    /**
     * 基本类型的转换
     * 
     * @param value 传入要转换的值
     * @return
     */
    public String getValue(Object value) {
        String textValue = "";
        if (value == null) {
            return textValue;
        }

        if (value instanceof String) {
            textValue = "\"" + value.toString() + "\"";
        } else if (value instanceof Date) {
            Date date = (Date) value;
            textValue = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);

        } else {
            textValue = "\t" + value.toString();
        }
        return textValue;
    }

    /**
     * 空判断，null,"null","", " " ," null "
     * 
     * @param s
     * @return
     */
    private boolean isNull(String s) {
        if (s == null || "null".equals(s.trim()) || "".equals(s.trim())) {
            return true;
        }
        return false;
    }
}
