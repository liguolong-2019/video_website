package com.cqupt.demo;

import com.cqupt.demo.Bean.Room;
import com.cqupt.demo.Bean.User;
import com.cqupt.demo.Dao.RoomDao;
import com.cqupt.demo.Dao.UserDao;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
@MapperScan("com.cqupt.demo")
class DemoApplicationTests {


    @Resource
    private RoomDao roomDao;
    @Resource
    private UserDao userDao;
    @Test
    void contextLoads() {
        List<Room> rooms = roomDao.allRooms();
        System.out.println(rooms);
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
    public void test01() {
        Room room = roomDao.queryById(1);
        System.out.println(room);
    }
}
