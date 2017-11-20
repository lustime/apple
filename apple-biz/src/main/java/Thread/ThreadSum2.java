/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: ThreadSum2.java
 * Author:   17040407
 * Date:     17-11-14 上午11:24
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
public class ThreadSum2 extends Thread {

    int sum = 0;

    public void run(){
        synchronized (this){
            for(int i = 0; i<101;i++){
                sum+=i;
            }
            notifyAll();
        }
    }
}
