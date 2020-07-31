package com.cqupt.demo;

import com.cqupt.demo.Bean.User;
import com.cqupt.demo.Dao.UserDao;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
@MapperScan("com.cqupt.demo")
class DemoApplicationTests {

    @Resource
    private UserDao userDao;
    @Test
    void contextLoads() {
        System.out.println(userDao.selectAll());
        int i = userDao.insertUser(new User(2, "zlw", 19, "143", "password"));
        System.out.println(i);
    }

}
