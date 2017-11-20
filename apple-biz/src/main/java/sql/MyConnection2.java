/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: MyConnection2.java
 * Author:   17040407
 * Date:     17-8-22 下午3:07
 * Description: 智能风控系统
 * History: //修改记录
 * <author> <time> <version> <desc>
 * 修改人姓名      修改时间      版本号        描述
 */

package sql;

import org.junit.Test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Properties;
import java.util.Random;
import java.util.UUID;

/**
 * Created by 17040407 on 2017/6/29.
 * 效率较高的批量插入(mysql 5.1.13加了rewriteBatchedStatements参数，插入速度显著提升)
 */
public class MyConnection2 {
    private static String url;
    //  private String url = "jdbc:mysql://localhost:3306/pcidsloc?rewriteBatchedStatements=true";
    private static String user;
    private static String password;
    private static String driverClass;

    static {
        Properties properties = new Properties();
        try {
            properties.load(MyConnection.class.getClassLoader().getResourceAsStream("jdbc.properties"));
            url = properties.getProperty("jdbcUrl");
            user = properties.getProperty("username");
            password = properties.getProperty("password");
            driverClass = properties.getProperty("className");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Client 1.
     */
    @Test
    public void Test1() {
        System.out.println(url);
        System.out.println(user);
        System.out.println(password);
        System.out.println(driverClass);
    }

    /**
     * Client.
     */
    @Test
    public void Test() {
        Long startTime = System.currentTimeMillis();
        Connection conn;
        PreparedStatement ps;

        try {
            Class.forName(driverClass);
            conn = DriverManager.getConnection(url, user, password);
            conn.setAutoCommit(false);
            ps = conn.prepareStatement("");

            String prefix = "insert into pcids_interface_invoke_log(uuid,invoke_interface,invoke_interface_description,is_exception,input_message,return_message,invoke_time) values ";

            StringBuffer suffix = new StringBuffer();
            int k = 1;
            int a, b, c, d;
            Random rand = new Random();
            for (int i = 1; i <= 300; i++) {
                Long time1 = System.currentTimeMillis();

                for (int j = 1; j <= 10000; j++) {
                    a = rand.nextInt(10);
                    b = rand.nextInt(10);
                    c = rand.nextInt(10);
                    d = rand.nextInt(10);
                    String uuid = UUID.randomUUID().toString().replaceAll("-", "");
                    //     suffix.append("('1554856886884543', '正常', '612767.00', '194.00', '4579.00', '2017.01.06', '4579.00', '2017.01.06', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '2015.02.', 'NNNNNNNNNNNNNNNNNNNNNNNN', '1.2013年03月06日商业银行“OZ”发放的690,000元（人民币）个人住房贷款，业务号X，抵押担保，240期，按月归还，2033年03月06日到期。截至2017年01月06日，', '正常', '2017011600003560875618', '2013.03.06', '商业银行', 'OZ', '690000.0', '人民币', '个人住房贷款', 'X', '抵押担保', '240.0', '按月归还', '2033.03.06', '2017.01.06', '', '', '2017-01-17 22:39:24', '2017-02-27 00:00:00', 'asd','"+a+""+b+""+c+""+d+""+a*b+""+c+d+""+"xxxxxxxx"+c+d+""+a+d+"x"+a+"',CONCAT('姓名','"+(k++)+"')),");
                    suffix.append("('" + uuid + "', 'preCreditService', '小贷调预受信', '0', '{\\\"uuid\\\":\\\"05747c3346d6498483bc7421cd520494\\\",\\\"channel\\\":\\\"RXD\\\",\\\"prodId\\\":\\\"RXD_PRECA\\\",\\\"useSide\\\":\\\"\\\",\\\"accountNo\\\":\\\"0000000000002039174\\\",\\\"cardType\\\":\\\"01\\\",\\\"cardId\\\":\\\"420505201111074490\\\",\\\"realName\\\":\\\"张小四\\\"}', '{\\\"accountNo\\\":\\\"0000000000002039174\\\",\\\"bizKVs\\\":\\\"\\\",\\\"cardId\\\":\\\"420505201111074490\\\",\\\"cardType\\\":\\\"01\\\",\\\"companyCode\\\":\\\"01\\\",\\\"customerType\\\":\\\"99\\\",\\\"isAdmit\\\":\\\"1\\\",\\\"prodId\\\":\\\"RXD\\\",\\\"resultCode\\\":\\\"0001\\\",\\\"resultDesc\\\":\\\"preCredit is fail,no data in staffIndexInfo\\\",\\\"useSide\\\":\\\"RXD_PRECA\\\",\\\"uuid\\\":\\\"05747c3346d6498483bc7421cd520494\\\"}', '2017-08-21 18:15:10'),");
                }
                String sql = prefix + suffix.substring(0, suffix.length() - 1);
                ps.addBatch(sql);
                ps.executeBatch();
                conn.commit();
                suffix.delete(0, suffix.length());

                Long time2 = System.currentTimeMillis();

                System.out.println("第" + i + "次提交入库耗时：" + (time2 - time1));
            }
            ps.close();
            conn.close();
            Long endTime = System.currentTimeMillis();

            System.out.println("耗时：" + (endTime - startTime));

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
