package com.suning.groovyTest

/**
 * Created by 17040407 on 2017/6/22.
 */
class ExampleFile {
    static void main(String[] args)
    {
        /*new File("d:/pcidsdev.sql").eachLine {
            line -> println "$line"
        }*/
    //    file1()
        file4()

    }

    static void file1()
    {
        new File("d:/pcidsdev.sql").eachLine {
            line -> println(line)
        }
    }

    static void file2()
    {
        File file = new File("d:/pcidsdev.sql")
        println(file.text)
    }

    static void file3()
    {
        new File('E:/','pcidsdev.sql').withWriter('utf-8') {
            writer -> writer.writeLine 'hehe'
        }
    }

    static void file4()
    {
        def src=new File('d:/pcidsdev.sql')
        def dst=new File('d:/copy.sql')
        dst<<src.text
    }
}
