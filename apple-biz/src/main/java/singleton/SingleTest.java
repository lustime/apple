/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: SingleTest.java
 * Author:   17040407
 * Date:     17-8-25 上午11:53
 * Description: 智能风控系统
 * History: //修改记录
 * <author> <time> <version> <desc>
 * 修改人姓名      修改时间      版本号        描述
 */

package singleton;

import org.junit.Test;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 *
 * @author 17040407
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class SingleTest {
    @Test
    public void test1(){
        Runnable runnable=new Runnable() {
            public void run() {
                String threadName=Thread.currentThread().getName();
                Single s=Single.getInstance();
                System.out.println("线程"+threadName+":"+s.hashCode());
            }
        };

        for(int i=0;i<100;i++){
            new Thread(runnable,""+i).start();
        }
    }

    @Test
    public void singleTonEH(){
        Runnable runnable=new Runnable() {
            public void run() {
                String threadName=Thread.currentThread().getName();
                SingleTonEH s=SingleTonEH.getInstance();
                System.out.println("线程"+threadName+":"+s.hashCode());
            }
        };

        for(int i=0;i<100;i++){
            new Thread(runnable,""+i).start();
        }
    }

    @Test
    public void singleTonLH(){
        Runnable runnable=new Runnable() {
            public void run() {
                String threadName=Thread.currentThread().getName();
                SingleTonLH s=SingleTonLH.getInstance();
                System.out.println("线程"+threadName+":"+s.hashCode());
            }
        };

        for(int i=0;i<100;i++){
            new Thread(runnable,""+i).start();
        }
    }

    @Test
    public void singleTonLH2(){
        Runnable runnable=new Runnable() {
            public void run() {
                String threadName=Thread.currentThread().getName();
                SingleTonLH2 s=SingleTonLH2.getInstance();
                System.out.println("线程"+threadName+":"+s.hashCode());
            }
        };

        for(int i=0;i<1000;i++){
            new Thread(runnable,""+i).start();
        }
    }
}
