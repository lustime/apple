/*
 * Copyright (C), 2002-2018, 苏宁易购电子商务有限公司
 * FileName: CsvExportUtil.java
 * Author:   17040407
 * Date:     18-1-5 上午9:11
 * Description: 智能风控系统
 * History: //修改记录
 * <author> <time> <version> <desc>
 * 修改人姓名      修改时间      版本号        描述
 */

package csvUtil;

import com.google.common.collect.Lists;
import com.suning.framework.log.StringUtils;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 导出.csv格式文件公共类<br>
 * 〈功能详细描述〉
 *
 * @author 17040407
 * @see [相关类/方法]（可选）
 * @since [产品 /模块版本] （可选）
 */
public class CsvExportUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(CsvExportUtil.class);
    private static final String LINE_SEPARATOR = "\n";
    private static final String TEMP_PREFIX = "csv_";

    /**
     * Export.
     *
     * @param request      the request
     * @param response     the response
     * @param destFileName the dest file name
     * @param data         the data(key:the template file name,value:arrayList)
     */
    public static void export(HttpServletRequest request, HttpServletResponse response, String destFileName, Map<String, ArrayList> data) {
        OutputStream os = null;
        Path tempDirectory = null;
        ZipOutputStream zipOs = null;

        try {
            if (request.getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0) {
                destFileName = URLEncoder.encode(destFileName, "UTF-8");
            } else {
                destFileName = new String(destFileName.getBytes("UTF-8"), "ISO8859-1");
            }

            response.setHeader("Content-Disposition", "attachment;filename=" + destFileName + ".zip");
            response.setContentType("application/zip");
            response.setCharacterEncoding("utf-8");

            os = response.getOutputStream();
            zipOs = new ZipOutputStream(os);

            //创建临时目录
            tempDirectory = Files.createTempDirectory(TEMP_PREFIX);
            File file = tempDirectory.toFile();
            String realPath = tempDirectory.toString();

            csvWriter(realPath, data);
            zip(zipOs, file, realPath);
        } catch (Exception e) {
            LOGGER.error("io异常", e);
        } finally {
            if (null != zipOs) {
                try {
                    zipOs.close();
                } catch (IOException e) {
                    LOGGER.error("关闭ZipOutputStream流失败", e);
                }
            }
            if (null != os) {
                try {
                    os.close();
                } catch (IOException e) {
                    LOGGER.error("关闭OutputStream流失败！", os);
                }
            }
            if (null != tempDirectory) {
                try {
                    deleteIfExists(tempDirectory);  //删除临时文件
                } catch (IOException e) {
                    LOGGER.error("删除临时文件失败", e);
                }
            }
        }
    }

    /**
     * 压缩文件
     *
     * @param out
     * @param f
     * @param fileName
     */
    private static void zip(ZipOutputStream out, File f, String fileName) {
        if (f.isDirectory()) {
            File[] files = f.listFiles();
            if (null != files) {
                for (File file : files) {
                    zip(out, file, file.getName());
                }
            }
        } else {
            FileInputStream fis = null;
            try {
                out.putNextEntry(new ZipEntry(fileName));
                fis = new FileInputStream(f);
                byte[] buffer = new byte[1024];
                while (fis.read(buffer) > 0) {
                    out.write(buffer);
                }
            } catch (IOException e) {
                LOGGER.error("IO异常", e);
            } finally {
                if (null != fis) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        LOGGER.error("关闭FileInputStream流失败", e);
                    }
                }
            }
        }
    }

    /**
     * 删除临时目录
     * @param dir
     * @throws IOException
     */
    private static void deleteIfExists(Path dir) throws IOException {
        try {
            Files.deleteIfExists(dir);
        } catch (DirectoryNotEmptyException e) {
            Files.walkFileTree(dir, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    Files.delete(file);
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                    if (null == exc) {
                        Files.delete(dir);
                        return FileVisitResult.CONTINUE;
                    } else {
                        throw exc;
                    }
                }
            });
        }
    }

    /**
     * 根据模板写入csv文件
     * @param realPath
     * @param dataField
     */
    private static void csvWriter(String realPath, Map<String, ArrayList> dataField) {
        BufferedReader br = null;
        InputStream in = null;
        FileWriter fileWriter = null;
        CSVPrinter csvPrinter = null;

        CSVFormat csvFormat = CSVFormat.DEFAULT.withRecordSeparator(LINE_SEPARATOR);

        for (Map.Entry<String, ArrayList> entry : dataField.entrySet()) {
            String tempFileName = entry.getKey() + ".csv";  //  模板文件
            try {
                fileWriter = new FileWriter(realPath + "\\" + tempFileName);   //  临时目录
                List data = entry.getValue();
                Iterator iterator = data.iterator();

                /** 取得实例 */
                Object object = iterator.next();
                in = CsvExportUtil.class.getResourceAsStream("/conf/exportCsv/" + tempFileName);
                br = new BufferedReader(new InputStreamReader(in, "UTF-8"));
                String fieldName = br.readLine();   //模板表头行
                String fieldLine = br.readLine();   //导出字段名
                if (!StringUtils.isNotEmpty(fieldLine) && !StringUtils.isNotEmpty(fieldName)) {
                    LOGGER.error("csv模板文件为空");
                    return;
                }
                csvPrinter = new CSVPrinter(fileWriter, csvFormat);

                String[] exportFieldTitle = fieldLine.split(",");
                String[] exportFieldName = fieldName.split(",");
                csvPrinter.printRecord(exportFieldName);    //写入表头

                Class clazz = object.getClass();
                List<String> fieldTitleList = Arrays.asList(exportFieldTitle);

                // 导出的字段的get方法
                List<Method> methodList = new ArrayList<Method>();

                // 组装methodList
                for (String methodStr : fieldTitleList) {
                    String getMethodName = "get" + methodStr.substring(0, 1).toUpperCase() + methodStr.substring(1);
                    Method getMethod = null;
                    try {
                        getMethod = clazz.getMethod(getMethodName);
                    } catch (Exception e) {
                        LOGGER.info("方法不存在,存入null", e);
                    }
                    methodList.add(getMethod);
                }

                for (Object t : data) {
                    //反射赋值
                    List<String> records = Lists.newArrayList();
                    for (Method method : methodList) {
                        Object value = null;
                        if (null == method) {
                            records.add("");    //method为null,存入空串
                            continue;
                        }
                        try {
                            value = method.invoke(t);
                        } catch (Exception e) {
                            LOGGER.info("方法不存在,存入null", e);
                        }
                        String valueStr = getValue(value);// 值
                        records.add(valueStr);
                    }
                    csvPrinter.printRecord(records);
                }
            } catch (Exception e) {
                LOGGER.error("写入csv文件异常！", e);
            } finally {
                try {
                    if (null != csvPrinter) {
                        csvPrinter.flush();
                        csvPrinter.close();
                    }
                    if (null != fileWriter) {
                        fileWriter.close();
                    }
                    if (null != br) {
                        br.close();
                    }
                    if (null != in) {
                        in.close();
                    }
                } catch (IOException e) {
                    LOGGER.error("io异常", e);
                }
            }

        }
    }

    /**
     * 基本类型的转换
     *
     * @param value 传入要转换的值
     * @return value value
     */
    private static String getValue(Object value) {
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
