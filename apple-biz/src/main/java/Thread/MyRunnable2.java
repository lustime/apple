/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: MyRunnable2.java
 * Author:   17040407
 * Date:     17-11-12 上午11:14
 * Description: 智能风控系统
 * History: //修改记录
 * <author> <time> <version> <desc>
 * 修改人姓名      修改时间      版本号        描述
 */

package Thread;

import Thread.pojo.Foo2;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 *
 * @author 17040407
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class MyRunnable2 {
    class MyThread extends Thread{
        private Foo2 foo2;

        private int y =0;

        MyThread(String name,Foo2 foo2,int y){
            super(name);
            this.foo2=foo2;
            this.y=y;
        }

        public void run(){
            foo2.fix(y);
        }
    }

    public static void main(String[] args) {
        MyRunnable2 run = new MyRunnable2();
        Foo2 foo2 = new Foo2();
        MyThread t1 = run.new MyThread("thread-A",foo2,10);
        MyThread t2 = run.new MyThread("thread-B",foo2,-3);
        MyThread t3 = run.new MyThread("thread-C",foo2,-2);
        MyThread t4 = run.new MyThread("thread-D",foo2,5);
        t1.start();
        t2.start();
        t3.start();
        t4.start();

    }
}
