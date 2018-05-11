/*
 * Copyright (C), 2002-2018, 苏宁易购电子商务有限公司
 * FileName: MyClassLoader.java
 * Author:   17040407
 * Date:     18-5-8 下午4:05
 * Description: 智能风控系统
 * History: //修改记录
 * <author> <time> <version> <desc>
 * 修改人姓名      修改时间      版本号        描述
 */

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 *
 * @author 17040407
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class MyClassLoader extends ClassLoader {

    private String root;

    public String getRoot() {
        return root;
    }

    public void setRoot(String root) {
        this.root = root;
    }

    @Override
    protected Class<?> findClass(String s) throws ClassNotFoundException {
        byte[] bytes = loadClassData(s);
        if (null == bytes) {
            throw new ClassNotFoundException();
        } else {
            return defineClass(s, bytes, 0, bytes.length);
        }

    }

    private byte[] loadClassData(String className) {
        String fileName = root + File.separatorChar
                + className.replace('.', File.separatorChar) + ".class";
        try {
            InputStream in = new FileInputStream(fileName);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int bufferSize = 1024;
            byte[] buffer = new byte[bufferSize];
            int length = 0;
            while ((length = in.read(buffer)) != -1) {
                baos.write(buffer, 0, length);
            }
            return baos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;


    }

    public static void main(String[] args) {
        MyClassLoader loader = new MyClassLoader();
        loader.setRoot("C:\\");
        Class<?> testClass;
        try {
            testClass = loader.findClass("algorithm.Test1");
            Object object = testClass.newInstance();
            System.out.println(object.getClass().getClassLoader());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }
}
