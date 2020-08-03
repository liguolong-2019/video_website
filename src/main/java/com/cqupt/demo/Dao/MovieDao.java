package com.cqupt.demo.Dao;

import com.cqupt.demo.Bean.Movie;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MovieDao {
    List<Movie> queryBy_Adid(@Param("adminId")Integer adminId);
}
