package com.cqupt.demo.Dao;

import com.cqupt.demo.Bean.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AdminDao {
    int insertAdmin(Admin admin);

    Admin selectByName(String adminName);

    Admin adlogin(@Param("adminName")String userName, @Param("password")String password);
}
