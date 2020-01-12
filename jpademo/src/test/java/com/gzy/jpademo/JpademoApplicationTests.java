package com.gzy.jpademo;


import com.gzy.jpademo.entity.User;
import com.gzy.jpademo.repository.RoomRepository;
import com.gzy.jpademo.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

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
}
