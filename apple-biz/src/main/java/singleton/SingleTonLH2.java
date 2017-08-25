/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: SingleTonLH2.java
 * Author:   17040407
 * Date:     17-8-25 下午3:07
 * Description: 智能风控系统
 * History: //修改记录
 * <author> <time> <version> <desc>
 * 修改人姓名      修改时间      版本号        描述
 */

package singleton;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 *
 * @author 17040407
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class SingleTonLH2 {
    private SingleTonLH2(){}

    private static SingleTonLH2 instance = null;

    public static SingleTonLH2 getInstance()
    {
        if(instance == null)
        {
            synchronized (SingleTonLH2.class)
            {
                if(instance == null)
                {
                    instance = new SingleTonLH2();
                }
            }
        }
        return instance;
    }
}
