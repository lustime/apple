package com.suning.lumin

import java.text.SimpleDateFormat

/**
 * Created by 17040407 on 2017/6/21.
 */
class GroovyTest {
    static void main(String[] args)
    {
        def now=new Date()
        //得到日历
        Calendar calendar = Calendar.getInstance()
        calendar.setTime(now)
        calendar.add(Calendar.MONTH,-3)
        def beforeDate=calendar.getTime()

        def bd=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(beforeDate)
        println bd


    }
}