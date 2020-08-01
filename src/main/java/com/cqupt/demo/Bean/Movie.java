package com.cqupt.demo.Bean;

public class Movie {
    private Integer movieId;
    private String movieName;
    private String src;
    private Integer adminId;


    public Movie() {

    }

    public Movie(Integer movieId, String movieName, String src, Integer adminId) {
        this.movieId = movieId;
        this.movieName = movieName;
        this.src = src;
        this.adminId = adminId;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }


    public Integer getMovieId() {
        return movieId;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }
}
