package com.cqupt.demo.Dao;

import com.cqupt.demo.Bean.Room;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RoomDao {

    int creatPublicRoom(@Param("roomName")String roomName,@Param("movieId")Integer movieId,@Param("userId")Integer userId);

    Room queryRoomByName(@Param("roomName")String roomName);

    Room queryRoomById(@Param("roomId") Integer roomId);

    List<Room> allRooms();

    Room queryById(@Param("roomId") Integer roomId);

    Room queryPri(@Param("roomId") Integer roomId,@Param("password") String password);

    int insertRoom(Room room);



}
