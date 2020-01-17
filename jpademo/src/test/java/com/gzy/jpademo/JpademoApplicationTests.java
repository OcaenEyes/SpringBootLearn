package com.gzy.jpademo;


import com.gzy.jpademo.entity.User;
import com.gzy.jpademo.repository.RoomRepository;
import com.gzy.jpademo.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.annotation.Resource;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class JpademoApplicationTests {

    @Test
    void contextLoads() {
    }

    @Resource
    private UserRepository userRepository;
    @Resource
    private RoomRepository roomRepository;

    @Test
    public void test01() {
        userRepository.save(new User(1l, "gzy"));
        userRepository.save(new User(2l, "zy"));

        int size = userRepository.findAll().size();
        System.out.println(size);

        User user = userRepository.findByUserId((long) 1);
        String s = user.toString();
        System.out.println("输出userId 为1 的账户");
        System.out.println(s);
    }

    @Autowired
    @Qualifier("primaryJdbcTemplate")
    private JdbcTemplate jdbcTemplate1;

    @Autowired
    @Qualifier("secondaryJdbcTemplate")
    private JdbcTemplate jdbcTemplate2;

    @Test
    public void dataSourceTest(){
        System.out.println("开始查询数据");
        SqlRowSet sqlRowSet = jdbcTemplate1.queryForRowSet("SELECT * FROM account ");
        System.out.println(sqlRowSet);
        SqlRowSet sqlRowSet1 = jdbcTemplate2.queryForRowSet("SELECT * FROM students");
        System.out.println(sqlRowSet1);
        System.out.println("查询数据结束");

    }
}
