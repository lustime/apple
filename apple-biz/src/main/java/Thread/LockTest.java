/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: LockTest.java
 * Author:   17040407
 * Date:     17-11-15 上午10:05
 * Description: 智能风控系统
 * History: //修改记录
 * <author> <time> <version> <desc>
 * 修改人姓名      修改时间      版本号        描述
 */

package Thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 *
 * @author 17040407
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class LockTest {
    class MyCount{
        private String oid; //账号

        private int cash;//账户余额

        public MyCount(String oid, int cash) {
            this.oid = oid;
            this.cash = cash;
        }

        public String getOid() {
            return oid;
        }

        public void setOid(String oid) {
            this.oid = oid;
        }

        public int getCash() {
            return cash;
        }

        public void setCash(int cash) {
            this.cash = cash;
        }

        @Override
        public String toString() {
            final StringBuffer sb = new StringBuffer("MyCount{");
            sb.append("账号='").append(oid).append('\'');
            sb.append(", 余额=").append(cash);
            sb.append('}');
            return sb.toString();
        }
    }

    class User implements Runnable{

        private String name;
        private MyCount myCount;
        private int iocash;
        private Lock lock;

        public User(String name, MyCount myCount, int iocash, Lock lock) {
            this.name = name;
            this.myCount = myCount;
            this.iocash = iocash;
            this.lock = lock;
        }

        public void run() {
            String option = iocash > 0 ? "存款" : "取款";
            lock.lock();
            System.out.println(name + "正在操作" + myCount + "账户," + option + "操作金额："+iocash+"当前余额为"+myCount.cash);
            myCount.setCash(myCount.getCash()+iocash);
            System.out.println(name + "操作" + myCount + "账户成功," + option + "操作金额："+iocash+"当前余额为"+myCount.cash);
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        LockTest lockTest = new LockTest();
        MyCount myCount = lockTest.new MyCount("6401211992",1000000);
        Lock lock = new ReentrantLock();
        ExecutorService pool = Executors.newCachedThreadPool();
        User u1 = lockTest.new User("张三", myCount, -4000, lock);
        User u2 = lockTest.new User("张三他爹", myCount, 6000, lock);
        User u3 = lockTest.new User("张三他弟", myCount, -8000, lock);
        User u4 = lockTest.new User("张三", myCount, 800, lock);

        pool.execute(u1);
        pool.execute(u2);
        pool.execute(u3);
        pool.execute(u4);

        pool.shutdown();
    }

}
