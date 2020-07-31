package com.cqupt.demo.Bean;

public class Movie {
    private Integer movieId;
    private String movieName;
    private String src;
    private Admin admin;


    public Movie() {

    }
    public Movie(Integer movieId, String movieName, String src, Admin admin) {
        this.movieId = movieId;
        this.movieName = movieName;
        this.src = src;
        this.admin = admin;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
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
