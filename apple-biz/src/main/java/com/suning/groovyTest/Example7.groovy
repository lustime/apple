package com.suning.groovyTest

/**
 * Created by 17040407 on 2017/6/23.
 */
class Example7 {
    static void main(String[] args)
    {
        def mp=["topicName":"map","topicDesc":"method in maps"]

        mp.each {println(it)}

        mp.each {println("${it.key} maps to ${it.value}")}
    }
}
