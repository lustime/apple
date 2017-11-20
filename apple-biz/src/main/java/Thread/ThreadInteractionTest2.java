/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: ThreadInteractionTest2.java
 * Author:   17040407
 * Date:     17-11-14 上午11:25
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
public class ThreadInteractionTest2 extends Thread {

    ThreadSum2 threadSum2;

    public ThreadInteractionTest2(ThreadSum2 threadSum2) {
        this.threadSum2 = threadSum2;
    }

    public void run(){
        synchronized (threadSum2){
            System.out.println("等待sum完成计算");
            try {
                threadSum2.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("计算结果："+threadSum2.sum);
        }
    }

    public static void main(String[] args) {
        ThreadSum2 threadSum2 = new ThreadSum2();
        new ThreadInteractionTest2(threadSum2).start();
        new ThreadInteractionTest2(threadSum2).start();
        new ThreadInteractionTest2(threadSum2).start();

        threadSum2.start();
    }
}
