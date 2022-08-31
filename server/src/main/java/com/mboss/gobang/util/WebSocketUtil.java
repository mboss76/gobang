package com.mboss.gobang.util;

import com.mboss.gobang.entity.ChessBoard;

import javax.websocket.RemoteEndpoint;
import javax.websocket.Session;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class WebSocketUtil {

    /**
     * 模拟存储   在线用户
     */
    public static final Map<String, Session> USERS_ONLINE = new ConcurrentHashMap<>();

    public static final Map<String, ChessBoard> CHESSBOARD_ONLINE = new ConcurrentHashMap<>();

    /**
     * 向所有在线用户发送消息(遍历 向每一个用户发送)
     * @param message
     */
    public static void sendMessageToUsers(Set<String> usernames, Object message){
        for(String username:usernames){
            if(USERS_ONLINE.containsKey(username)){
                sendMessage(USERS_ONLINE.get(username),message);
            }
        }
    }

    /**
     * 向指定用户发送消息
     * @param session 用户session
     * @param message 发送消息内容
     */
    private static void sendMessage(Session session, Object message) {
        if (session == null) {
            return;
        }

        final RemoteEndpoint.Basic basic = session.getBasicRemote();
        if (basic == null) {
            return;
        }
        try {
            basic.sendObject(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
