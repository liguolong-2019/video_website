package com.cqupt.demo.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint("/chat/{roomId}")
@Component
public class ChatController {
    private static Map<Integer, Set<Session>> rooms = new ConcurrentHashMap<>();
    private final Logger logger = LoggerFactory.getLogger(ChatController.class);

    @OnOpen
    public void open(@PathParam("roomId")Integer roomId, Session session){
        if (rooms.get(roomId) == null) {
            rooms.put(roomId,new HashSet<Session>());
        }
        Set<Session> room = rooms.get(roomId);
        room.add(session);
        logger.info("有新的客户端连接,session ID:{},room ID :{}",session.getId(),roomId);
    }

    @OnMessage
    public void onMessage(String msg,@PathParam("roomId")Integer roomId) throws IOException {
        Set<Session> room = rooms.get(roomId);
        for (Session session1 : room) {
            session1.getBasicRemote().sendText(msg);
        }
    }

    @OnClose
    public void onClose(@PathParam("roomId")Integer roomId,Session session){
        Set<Session> room = rooms.get(roomId);
        room.remove(session);
        logger.info("有客户端断开连接,session ID:{},room ID :{}",session.getId(),roomId);
    }

    @OnError
    public void onError(Throwable throwable){
        throwable.printStackTrace();
    }
}
