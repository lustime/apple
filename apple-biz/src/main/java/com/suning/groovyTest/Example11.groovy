/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: Example11.groovy
 * Author:   17040407
 * Date:     17-8-24 上午10:17
 * Description: 智能风控系统
 * History: //修改记录
 * <author> <time> <version> <desc>
 * 修改人姓名      修改时间      版本号        描述
 */

package com.suning.groovyTest


/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 *
 * @author 17040407
 * @see [ 相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
class Example11 {
    static void main(String[] args) {
        def str="abc123def"

        def pattern=/[0-9]+/

        println(str.matches(pattern))

        println(str.find(pattern))



    }
}
