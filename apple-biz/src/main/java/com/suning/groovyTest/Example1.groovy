package com.suning.groovyTest

/**
 * Created by 17040407 on 2017/6/22.
 */
class Example1 {
    static void main(String[] args)
    {
        def a=1..6
        println(a)
        println(a.get(3))
        println(add(9))
    }

    //参数可以有默认值
    static int add(int a,int b=1)
    {
        int c=a+b
        c
    }
}
