package sql;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Random;

/**
 * Created by 17040407 on 2017/6/29.
 * 效率较高的批量插入(mysql 5.1.13加了rewriteBatchedStatements参数，插入速度显著提升)
 */
public class MyConnection2 {
    public String url = "jdbc:mysql://localhost:3306/pcidsloc?rewriteBatchedStatements=true";
    public String user = "root";
    public String password = "root";
    public String driverClass = "com.mysql.jdbc.Driver";

    @Test
    public void Test()
    {
        Long startTime=System.currentTimeMillis();
        Connection conn;
        PreparedStatement ps;

        try {
            Class.forName(driverClass);
            conn=DriverManager.getConnection(url,user,password);
            conn.setAutoCommit(false);
            ps=conn.prepareStatement("");

            String prefix="insert into pcids_rhzx_loaninfo(loan_id,class5state,balance,remainpaymentcyc,scheduledpaymentamount,scheduledpaymentdate,actualpaymentamount,recentpaydate,curroverduecyc,curroverdueamount,overdue31to60amount,overdue61to90amount,overdue91to180amount,overdueover180amount,last24monthbeginmonth,latest24state,cue,state,report_id_cue,start_date_cue,org_type_cue,organname_cue,loan_amount_cue,loan_amount_show_cue,loan_type_cue,busi_no_cue,guarantee_type_cue,period_cue,payment_method_cue,end_date_cue,deadline_cue,account_status_cue,bad_balance_cue,createTime,updateTime,cardtype,cardno,customername) values ";

            StringBuffer suffix=new StringBuffer();
            int k=1;
            int a,b,c,d;
            Random rand=new Random();
            for(int i=1;i<=100;i++)
            {
                Long time1=System.currentTimeMillis();

                for(int j=1;j<=10000;j++)
                {
                    a = rand.nextInt(10);
                    b = rand.nextInt(10);
                    c = rand.nextInt(10);
                    d = rand.nextInt(10);
                    suffix.append("('1554856886884543', '正常', '612767.00', '194.00', '4579.00', '2017.01.06', '4579.00', '2017.01.06', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '2015.02.', 'NNNNNNNNNNNNNNNNNNNNNNNN', '1.2013年03月06日商业银行“OZ”发放的690,000元（人民币）个人住房贷款，业务号X，抵押担保，240期，按月归还，2033年03月06日到期。截至2017年01月06日，', '正常', '2017011600003560875618', '2013.03.06', '商业银行', 'OZ', '690000.0', '人民币', '个人住房贷款', 'X', '抵押担保', '240.0', '按月归还', '2033.03.06', '2017.01.06', '', '', '2017-01-17 22:39:24', '2017-02-27 00:00:00', 'asd','"+a+""+b+""+c+""+d+""+a*b+""+c+d+""+"xxxxxxxx"+c+d+""+a+d+"x"+a+"',CONCAT('姓名','"+(k++)+"')),");
                }
                String sql=prefix+suffix.substring(0,suffix.length()-1);
                ps.addBatch(sql);
                ps.executeBatch();
                conn.commit();
                suffix.delete(0,suffix.length());

                Long time2=System.currentTimeMillis();

                System.out.println("第"+i+"次提交入库耗时："+(time2-time1));
            }
            ps.close();
            conn.close();
            Long endTime=System.currentTimeMillis();

            System.out.println("耗时："+(endTime-startTime));

        } catch (Exception e) {
            e.printStackTrace();
        }



    }
}
