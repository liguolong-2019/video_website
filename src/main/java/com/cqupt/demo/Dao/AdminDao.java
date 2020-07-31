package com.cqupt.demo.Dao;

import com.cqupt.demo.Bean.Admin;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminDao {
    int insertAdmin(Admin admin);

    Admin selectByName(String adminName);


}
