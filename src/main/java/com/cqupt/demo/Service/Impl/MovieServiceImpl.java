package com.cqupt.demo.Service.Impl;

import com.cqupt.demo.Bean.Movie;
import com.cqupt.demo.Dao.MovieDao;
import com.cqupt.demo.Service.MovieService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class MovieServiceImpl implements MovieService {

    @Resource
    private MovieDao movieDao;

    @Override
    public int addMovie(Movie movie) {
        return movieDao.insertMovie(movie);
    }

    @Override
    public List<Movie> queryAll() {
        return movieDao.queryAll();
    }

    @Override
    public List<Movie> queryBy_Adid(Integer adminId) {
        return movieDao.queryBy_Adid(adminId);
    }

    @Override
    public int removeMovie(Integer movieId) {
        return movieDao.deleteMovie(movieId);
    }
    @Override
    public Movie getById(Integer movieId) {
        return movieDao.queryById(movieId);
    }
}
