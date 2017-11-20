/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: MyRunnable.java
 * Author:   17040407
 * Date:     17-11-12 上午10:14
 * Description: 智能风控系统
 * History: //修改记录
 * <author> <time> <version> <desc>
 * 修改人姓名      修改时间      版本号        描述
 */

package Thread;

import Thread.pojo.Foo;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 *
 * @author 17040407
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class MyRunnable implements Runnable {

    private Foo foo = new Foo();

    public void run() {
        for(int i =0;i<3;i++){
            this.fix(30);
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + ":当前foo对象x的值："+foo.getX());
        }
    }

    public static void main(String[] args) {
        MyRunnable myRunnable = new MyRunnable();
        Thread ta = new Thread(myRunnable,"Thread-A");
        Thread tb = new Thread(myRunnable,"Thread-B");
        ta.start();
        tb.start();
    }

    public int fix(int y){
        return foo.fix(y);
    }
}
