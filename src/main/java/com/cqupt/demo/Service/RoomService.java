package com.cqupt.demo.Service;

import com.alibaba.fastjson.JSONObject;

public interface RoomService {
    JSONObject creatPublicRoom(String roomName, String movieName, Integer userId);
    JSONObject enterPublicRoom(Integer roomId);
    JSONObject rooms();
}
