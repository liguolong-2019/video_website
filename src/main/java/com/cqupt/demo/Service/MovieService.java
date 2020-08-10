package com.cqupt.demo.Service;

import com.cqupt.demo.Bean.Movie;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MovieService {
    int addMovie(Movie movie);

    List<Movie> queryAll();
    List<Movie> queryBy_Adid(@Param("adminId")Integer adminId);

    Movie getById(Integer movieId);


    int removeMovie(Integer movieId);
}
