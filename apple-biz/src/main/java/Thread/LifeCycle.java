/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: LifeCycle.java
 * Author:   17040407
 * Date:     17-11-13 下午7:47
 * Description: 智能风控系统
 * History: //修改记录
 * <author> <time> <version> <desc>
 * 修改人姓名      修改时间      版本号        描述
 */

package Thread;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 *
 * @author 17040407
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class LifeCycle extends Thread {

    public void run(){
        int n = 0;
        while (n++<1000000000L)
            System.out.println(n);

    }

    public static void main(String[] args) {
        LifeCycle thread =  new LifeCycle();
        System.out.println("isAlive:" + thread.isAlive());
        thread.start();
        System.out.println("isAlive2:"+ thread.isAlive());
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("isAlive3:"+ thread.isAlive());


    }
}
