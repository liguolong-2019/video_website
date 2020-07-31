package com.cqupt.demo.Dao;

import com.cqupt.demo.Bean.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AdminDao {
    Admin adlogin(@Param("adminName")String userName, @Param("password")String password);
}
