package com.cqupt.demo.Service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.cqupt.demo.Bean.Movie;
import com.cqupt.demo.Bean.Room;
import com.cqupt.demo.Dao.MovieDao;
import com.cqupt.demo.Dao.RoomDao;
import com.cqupt.demo.Service.RoomService;
import com.cqupt.demo.utils.FfmpegImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.util.List;

@Service
public class RoomServiceImpl  implements RoomService {

    @Resource
    private RoomDao roomDao;
    @Resource
    private MovieDao movieDao;


    @Override
    public Room queryPri(Integer movieId, String password) {
        return roomDao.queryPri(movieId, password);
    }

    @Override
    public int addPriRoom(Room room) {
        return roomDao.insertRoom(room);
    }

    @Override
    public JSONObject creatPublicRoom(String roomName, Integer movieId, Integer userId) {
        boolean success;
        String Msg;
        JSONObject result=new JSONObject();
        JSONObject data=new JSONObject();
        JSONObject roomInfo=new JSONObject();
        Room room = roomDao.queryRoomByName(roomName);
        Movie movie =movieDao.queryById(movieId);
        if (movie==null||room!=null){
            success=false;
            Msg="电影不存在或房间名已存在";
        }
        else {
            roomDao.creatPublicRoom(roomName, movie.getMovieId(), userId);
            Room newRoom=roomDao.queryRoomByName(roomName);
            success=true;
            Msg="创建成功";
            String fileName = FfmpegImpl.getFileName(movie.getSrc());
            FfmpegImpl.pushStream(roomName, fileName);
            String prefixMovieSrc = FfmpegImpl.getPrefixMovieSrc(movie.getSrc());
            movie.setSrc(prefixMovieSrc + File.separator+roomName);
            roomInfo.put("movie",movie);
            roomInfo.put("roomId",newRoom.getRoomId());
            roomInfo.put("roomName",newRoom.getRoomName());
            data.put("room",roomInfo);
        }

        result.put("success",success);
        result.put("Msg",Msg);
        result.put("data",data);
        return result;
    }

    @Override
    public JSONObject enterPublicRoom(Integer roomId) {
        boolean success;
        JSONObject result=new JSONObject();
        JSONObject data=new JSONObject();
        JSONObject roomInfo=new JSONObject();
        Room room = roomDao.queryRoomById(roomId);
        if (room!=null){
            Movie movie = movieDao.queryById(room.getMovieId());
            String src = movie.getSrc();
            String roomName = room.getRoomName();
            String movieSrc = src.substring(0, src.lastIndexOf("/")) +File.separator+ roomName;
            movie.setSrc(movieSrc);
            success=true;
            roomInfo.put("roomName",room.getRoomId());
            roomInfo.put("roomId",room.getRoomId());
            roomInfo.put("movie",movie);
            data.put("room",roomInfo);
        }
        else {
            success=false;
        }

        result.put("success",success);
        result.put("data",data);
        return result;
    }


    @Override
    public int removeRoom(Integer roomId) {
        return roomDao.deleteRoom(roomId);
    }

    public List<Room> rooms() {
        return roomDao.allRooms();
    }
}
