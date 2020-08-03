package com.cqupt.demo.Dao;

import com.cqupt.demo.Bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserDao {
    List<User> selectAll();

    int insertUser(User user);

    User selectUser(String userName);

    User login(@Param("userName")String userName, @Param("password")String password);
}
