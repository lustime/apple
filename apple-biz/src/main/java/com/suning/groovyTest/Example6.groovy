package com.suning.groovyTest

/**
 * Created by 17040407 on 2017/6/23.
 */
class Example6 {
    def static display(clo)
    {
        clo.call("hello")
    }

    static void main(String[] args)
    {
        def str="say"
        def clo={println("${str} ${it}")}
        clo.call("hello")

        str="welcome"
        clo.call("world")

        Example6.display(clo)
    }
}
