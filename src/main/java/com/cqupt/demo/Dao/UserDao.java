package com.cqupt.demo.Dao;

import com.cqupt.demo.Bean.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserDao {
    List<User> queryAll();
}
