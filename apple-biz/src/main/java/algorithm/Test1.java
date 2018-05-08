/*
 * Copyright (C), 2002-2018, 苏宁易购电子商务有限公司
 * FileName: Test1.java
 * Author:   17040407
 * Date:     18-5-8 下午5:39
 * Description: 智能风控系统
 * History: //修改记录
 * <author> <time> <version> <desc>
 * 修改人姓名      修改时间      版本号        描述
 */

package algorithm;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 *
 * @author 17040407
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class Test1 {

    public static long fib(int n){
        if(n <= 0)
            return 1;
        else
            return fib(n-1) + fib(n-2);
    }

    public static void main(String[] args) {

        System.out.println(fib(40));
    }

    public static boolean isTrue(){
        return false;
    }


}
