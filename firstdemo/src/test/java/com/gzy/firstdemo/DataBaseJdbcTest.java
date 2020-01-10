package com.gzy.firstdemo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

// 由于引入websocket, 所以需要为测试创建tomcat环境
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class DataBaseJdbcTest {
    @Autowired
    @Qualifier("primaryJdbcTemplate")
    private JdbcTemplate jdbcTemplate1;

    @Autowired
    @Qualifier("secondJdbcTemplate")
    private JdbcTemplate jdbcTemplate2;

    @Test
    public void test() throws Exception {
        System.out.println("开始查询数据");
        SqlRowSet sqlRowSet = jdbcTemplate1.queryForRowSet("SELECT * FROM account ");
        System.out.println(sqlRowSet);
        SqlRowSet sqlRowSet1 = jdbcTemplate2.queryForRowSet("SELECT * FROM students");
        System.out.println(sqlRowSet1);
        System.out.println("查询数据结束");

    }

}
