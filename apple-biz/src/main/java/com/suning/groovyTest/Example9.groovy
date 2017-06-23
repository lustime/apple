package com.suning.groovyTest

import groovy.sql.Sql

/**
 * Created by 17040407 on 2017/6/23.
 */
class Example9 {
    static void main(String[] args)
    {
        def sql=Sql.newInstance("jdbc:mysql://localhost:3306/pcidsloc",'root','root','com.mysql.jdbc.Driver')

        sql.eachRow('SELECT VERSION()'){row ->
            println row[0]
        }

        def sqlstr="select * from pcids_check_dictionary"

        sql.eachRow("""select * from pcids_check_dictionary"""){
            tp ->
                println("${tp.check_Type},"+"${tp.check_item}")
        }
    }
}
