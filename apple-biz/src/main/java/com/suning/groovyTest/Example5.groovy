package com.suning.groovyTest

/**
 * Created by 17040407 on 2017/6/23.
 */
class Example5 {
    static void main(String[] args)
    {
        def close={param->println("hello ${param}")}
        close.call("word")

        def clos={println("hello ${it}")}
        clos.call("groovy")

        def clos1={param1,param2->println("${param1} ${param2}")}
        clos1.call("hello","China")
    }
}
