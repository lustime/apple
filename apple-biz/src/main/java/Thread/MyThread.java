/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: MyThread.java
 * Author:   17040407
 * Date:     17-11-13 下午8:04
 * Description: 智能风控系统
 * History: //修改记录
 * <author> <time> <version> <desc>
 * 修改人姓名      修改时间      版本号        描述
 */

package Thread;

import java.util.Date;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 *
 * @author 17040407
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class MyThread extends Thread {
    class SleepThread extends Thread{
        public void run(){
            try {
                this.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void run(){
        while (true)
            System.out.println(new Date().getTime());
    }

    public static void main(String[] args) throws InterruptedException {
        MyThread myThread = new MyThread();
        SleepThread sleepThread = myThread.new SleepThread();
        sleepThread.start();
        sleepThread.join();
        myThread.start();
        boolean flag = false;
        while (true){
            sleep(5000);
            System.out.println("sleep 5s");
            flag = !flag;
            if(flag)
                myThread.suspend();
            else
                myThread.resume();
        }
    }
}
