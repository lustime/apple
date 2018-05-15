package Thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 *
 * @author
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class Test {
    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 200, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<Runnable>(5));

        for(int i=0;i<15;i++){
            MyTask myTask = new MyTask(i);
            executor.execute(myTask);
            System.out.println("线程池中的线程数量"+executor.getPoolSize()+",队列中等待执行的任务数量："
                    +executor.getQueue().size()+",已执行完的的任务数："
                    +executor.getCompletedTaskCount());

        }
        executor.shutdown();
    }

    static class MyTask implements Runnable{
        private int taskNum;

        public MyTask(int taskNum) {
            this.taskNum = taskNum;
        }

        @Override
        public void run() {
            System.out.println("正在执行task"+taskNum);
    //            try {
    //                Thread.currentThread().sleep(4000);
    //            } catch (InterruptedException e) {
    //                e.printStackTrace();
    //            }
            System.out.println("task"+taskNum+"执行完毕");
        }
    }

}
