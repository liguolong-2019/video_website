package com.cqupt.demo;

import com.cqupt.demo.Bean.Room;
import com.cqupt.demo.Bean.User;
import com.cqupt.demo.Dao.RoomDao;
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
    @Resource
    private RoomDao roomDao;
    @Test
    void contextLoads() {
        System.out.println(userDao.selectAll());
        int i = userDao.insertUser(new User(2, "zlw", 19, "143", "password"));
        System.out.println(i);
    }

    @Test
    void update(){
        User user1 = userDao.selectUserById(2);
        user1.setAge(100);
        user1.setEmail("2874277576@qq.com");
        user1.setUserName("lalal");
        int i = userDao.updateUserInfoByName(user1);
        System.out.println(i);
    }

    @Test
    void da(){
       // roomDao.creatPublicRoom("liguolong",2,3);
        Room aaa = roomDao.queryRoomByName("aaa");
        System.out.println(aaa);
    }
}
