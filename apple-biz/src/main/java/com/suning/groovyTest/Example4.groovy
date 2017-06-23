package com.suning.groovyTest

/**
 * Created by 17040407 on 2017/6/23.
 */
class Example4 {
    static void main(String[] args)
    {
        ListType<String> lststr=new ArrayList<String>()
        lststr.set("first string")
        println(lststr.get())

        ListType<Integer> lstint=new ArrayList<String>()
        lstint.set(1)
        println(lstint.get())

    }

}

class ListType<T>
{
    private T local
     T get()
    {
        return this.local
    }

     void set(T plocal)
    {
        this.local=plocal
    }
}
