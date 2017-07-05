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
    public String url = "jdbc:mysql://10.37.88.99:3306/pcidspre?rewriteBatchedStatements=true";
    public String user = "selffabu";
    public String password = "C163cRQzrU";
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

            String prefix="insert into pcids_rhzx_loancardinfo(loan_card_id,sharecreditlimitamount,usedcreditlimitamount,latest6monthusedavgamount,usedhighestamount,scheduledpaymentamount,scheduledpaymentdate,actualpaymentamount,recentpaydate,curroverduecyc,curroverdueamount,last_24state,cue,state,report_id_cue,start_date_cue,org_type_cue,organname_cue,card_type_cue,currency_cue,busi_no_cue,creditlimitamount_cue,sharecreditlimitamount_cue,guarantee_type_cue,deadline_cue,account_status_cue,bad_balance_cue,createTime,updateTime,cardtype,cardno,customername) values ";

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
               //     suffix.append("('1554856886884543', '正常', '612767.00', '194.00', '4579.00', '2017.01.06', '4579.00', '2017.01.06', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '2015.02.', 'NNNNNNNNNNNNNNNNNNNNNNNN', '1.2013年03月06日商业银行“OZ”发放的690,000元（人民币）个人住房贷款，业务号X，抵押担保，240期，按月归还，2033年03月06日到期。截至2017年01月06日，', '正常', '2017011600003560875618', '2013.03.06', '商业银行', 'OZ', '690000.0', '人民币', '个人住房贷款', 'X', '抵押担保', '240.0', '按月归还', '2033.03.06', '2017.01.06', '', '', '2017-01-17 22:39:24', '2017-02-27 00:00:00', 'asd','"+a+""+b+""+c+""+d+""+a*b+""+c+d+""+"xxxxxxxx"+c+d+""+a+d+"x"+a+"',CONCAT('姓名','"+(k++)+"')),");
                    suffix.append("(null, '', '0', '0', '1006', '0', '2017.05.07', '0', '2017.02.22', '0', '0', '*NNNNN**NNNNNN***N******', '1.2014年10月14日商业银行“IP”发放的贷记卡（人民币账户），业务号X，授信额度26,500元，共享授信额度26,500元，信用/免担保。截至2017年05月07日，', '正常', null, '2014.10.14', '商业银行', 'IP', '贷记卡', '人民币账户', 'X', '26500', '26500', '信用/免担保', '2017.05.07', '', '', '2017-06-29 16:33:39', '2017-06-29 16:33:39', '01', '"+a+""+b+""+c+""+d+""+a*b+""+c+d+""+"xxxxxxxx"+c+d+""+a+d+"x"+a+"',CONCAT('姓名','"+(k++)+"')),");
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
