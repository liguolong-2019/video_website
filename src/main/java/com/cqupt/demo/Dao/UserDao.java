package com.cqupt.demo.Dao;

import com.cqupt.demo.Bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserDao {
    List<User> queryAll();
    User login(@Param("userName")String userName,@Param("password")String password);
}
