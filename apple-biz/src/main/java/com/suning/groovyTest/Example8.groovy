package com.suning.groovyTest

/**
 * Created by 17040407 on 2017/6/23.
 */
class Example8 {
    static void main(String[] args)
    {
        def lst=[1,2,3,4]
//        lst.each {println(it)}
//        lst.each {println("${it}")}
//        lst.each {param->println(param)}
//        lst.each {param -> println("$param")}
//        lst.each {num ->
//            if(num % 2 ==0)println num}

        println lst.find{ num -> num>2 }

        def a=lst.findAll{num -> num%2==0}
        println a

        def b = ["Gam":23,"Jim":18,"Bob":50].any {
            age -> age.value>20
        }
        println b

        def c = ["Gam":23,"Jim":18,"Bob":50].collect {
            age -> ++age.value
                age
        }
        println(c)

        def list = (0..<9).collect {
            element ->  2*element
        }
        println(list)

    }


}
