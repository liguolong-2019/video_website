package com.cqupt.demo.Dao;

import com.cqupt.demo.Bean.Movie;
import com.cqupt.demo.Bean.Room;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MovieDao {

    List<Movie> queryBy_Adid(@Param("adminId")Integer adminId);

    List<Movie> queryAll();

    Movie queryByName(@Param("movieName")String movieName);

    Movie queryById(@Param("movieId") Integer movieId);

    int insertMovie(Movie movie);

    int deleteMovie(@Param("movieId") Integer movieId);

    int updateMovie(Movie movie);

}
