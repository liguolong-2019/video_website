package com.cqupt.demo.Bean;

public class Room {
    private Integer roomId;
    private String roomName;
    private Integer movieId;
    private Integer userId;
    private String password;
    private String type;

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

    public Integer getMovieId() {
        return movieId;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Room() {
    }

    public Room(Integer roomId, String roomName, Integer movieId, Integer userId, String password, String type) {
        this.roomId = roomId;
        this.roomName = roomName;
        this.movieId = movieId;
        this.userId = userId;
        this.password = password;
        this.type = type;
    }
}
