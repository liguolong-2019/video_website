package com.cqupt.demo.Service;

import com.alibaba.fastjson.JSONObject;
import com.cqupt.demo.Bean.Room;

public interface RoomService {
    Room queryPri(Integer movieId, String password);

    int addPriRoom(Room room);

    JSONObject creatPublicRoom(String roomName, Integer movieId, Integer userId);
    JSONObject enterPublicRoom(Integer roomId);
    JSONObject rooms();
}
