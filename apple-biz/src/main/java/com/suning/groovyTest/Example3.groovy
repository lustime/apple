package com.suning.groovyTest

/**
 * Created by 17040407 on 2017/6/23.
 */
class Example3 {
    static void main(String[] args)
    {
        Outer out=new Outer()
        out.name="Jim"
        out.callInnerMethod()
    }

}

class Outer{

    String name

    def callInnerMethod(){
        new Inner().methodA()
    }

    class Inner{
        def methodA()
        {
            println name
        }
    }
}
