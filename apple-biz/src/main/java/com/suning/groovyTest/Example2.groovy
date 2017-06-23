package com.suning.groovyTest

/**
 * Created by 17040407 on 2017/6/22.
 */
class Example2 {
    static void main(String[] args) {
//        test()
        test1()
    }


    static void test() {
        Integer x = 5, y = 10, z = 0
        z = x + y
        println(z)
    }

    static void test1()
    {
        println ('groovy' ==~ /^*vy$/)
    }
}
