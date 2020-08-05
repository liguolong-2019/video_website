package com.cqupt.demo.Bean;

public class Room {
    private Integer roomId;
    private String roomName;
    private Movie movie;
    private User user;
    private String password;
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Room() {

    }
    public Room(Integer roomId, String roomName, Movie movie, User user, String password,String type) {
        this.roomId = roomId;
        this.roomName = roomName;
        this.movie = movie;
        this.user = user;
        this.password = password;
        this.type=type;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
