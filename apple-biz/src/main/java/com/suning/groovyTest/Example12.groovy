/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: Example12.groovy
 * Author:   17040407
 * Date:     17-8-24 上午11:30
 * Description: 智能风控系统
 * History: //修改记录
 * <author> <time> <version> <desc>
 * 修改人姓名      修改时间      版本号        描述
 */

package com.suning.groovyTest

import java.util.regex.Matcher
import java.util.regex.Pattern

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 *
 * @author 17040407
 * @see [ 相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
class Example12 {
    static void main(String[] args) {

        def input="<H1>Chapter 1 - 介绍正则表达式</H1>"

        def pattern1=/<.*>/
        def pattern2=/<.*?>/
        def pattern3=/<\w+?>/

        println(input.find(pattern1))
        println(input.find(pattern2))
        println(input.find(pattern3))

        def str="Is is the cost of of gasoline going up up ?"
        def pattern=/(?i)\b([a-z]+) \1\b/

        Pattern p=Pattern.compile(pattern)
        Matcher m=p.matcher(str)
        while (m.find())
            println(m.group())
      //  println(str.find(pattern4))

        //非获取匹配
        def str1="helloWord"
        def pt=/hello(?:W)/
        def pt1=/hello(?=W|r)/
        def pt2=/hello(?!d|r)/

        println(str1.find(pt))
        println(str1.find(pt1))
        println(str1.find(pt2))
    }
}
