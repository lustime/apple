/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: Foo.java
 * Author:   17040407
 * Date:     17-11-12 上午10:05
 * Description: 智能风控系统
 * History: //修改记录
 * <author> <time> <version> <desc>
 * 修改人姓名      修改时间      版本号        描述
 */

package Thread.pojo;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 *
 * @author 17040407
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class Foo {
    private int x = 100;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int fix(int y){
        x = x-y;
        return x;
    }
}
