package sql;

import org.junit.Test;

import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import java.util.Random;

/**
 * Created by 17040407 on 2017/6/28.
 */
public class MyConnection {
    private static String url;
    //  private String url = "jdbc:mysql://localhost:3306/pcidsloc?rewriteBatchedStatements=true";
    private static String user;
    private static String password;

    static {
        Properties properties = new Properties();
        try {
            properties.load(MyConnection.class.getClassLoader().getResourceAsStream("jdbc.properties"));
            url = properties.getProperty("jdbcUrl");
            user = properties.getProperty("username");
            password = properties.getProperty("password");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void Test1() {
        System.out.println(url);
        System.out.println(user);
        System.out.println(password);
    }

    @Test
    public void Test() {
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rt = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
            String sql = "INSERT INTO pcids_rhzx_loaninfo(loan_id,class5state,balance,remainpaymentcyc,scheduledpaymentamount,scheduledpaymentdate,actualpaymentamount,recentpaydate,curroverduecyc,curroverdueamount,overdue31to60amount,overdue61to90amount,overdue91to180amount,overdueover180amount,last24monthbeginmonth,latest24state,cue,state,report_id_cue,start_date_cue,org_type_cue,organname_cue,loan_amount_cue,loan_amount_show_cue,loan_type_cue,busi_no_cue,guarantee_type_cue,period_cue,payment_method_cue,end_date_cue,deadline_cue,account_status_cue,bad_balance_cue,createTime,updateTime,cardtype,cardno,customername) VALUES('1554856886884543', '正常', '612767.00', '194.00', '4579.00', '2017.01.06', '4579.00', '2017.01.06', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '2015.02.', 'NNNNNNNNNNNNNNNNNNNNNNNN', '1.2013年03月06日商业银行“OZ”发放的690,000元（人民币）个人住房贷款，业务号X，抵押担保，240期，按月归还，2033年03月06日到期。截至2017年01月06日，', '正常', '2017011600003560875618', '2013.03.06', '商业银行', 'OZ', '690000.0', '人民币', '个人住房贷款', 'X', '抵押担保', '240.0', '按月归还', '2033.03.06', '2017.01.06', '', '', '2017-01-17 22:39:24', '2017-02-27 00:00:00', 'asd', ?,CONCAT('姓名',?))";
            pstm = conn.prepareStatement(sql);
            Long startTime = System.currentTimeMillis();
            Random rand = new Random();
            int a, b, c, d;
            for (int i = 1; i <= 1000000; i++) {
                //  pstm.setInt(1, i);
                pstm.setInt(2, i);
                a = rand.nextInt(10);
                b = rand.nextInt(10);
                c = rand.nextInt(10);
                d = rand.nextInt(10);
                //    pstm.setString(3, "188"+a+"88"+b+c+"66"+d);
                pstm.setString(1, a + "" + b + "" + c + "" + d + "" + a * b + "" + c + d + "" + "xxxxxxxx" + c + d + "" + a + d + "x" + a);
                pstm.addBatch();
            }
            pstm.executeBatch();
            Long endTime = System.currentTimeMillis();
            System.out.println("OK,用时：" + (endTime - startTime));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            if (pstm != null) {
                try {
                    pstm.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
            }
        }
    }


}
