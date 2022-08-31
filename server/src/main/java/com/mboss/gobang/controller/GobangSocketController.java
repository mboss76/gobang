package com.mboss.gobang.controller;

import com.mboss.gobang.entity.ChessBoard;
import com.mboss.gobang.entity.ResponseData;
import com.mboss.gobang.entity.WebSocketChessboardEncoder;
import com.mboss.gobang.util.RedisUtil;
import com.mboss.gobang.util.WebSocketUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Set;

@Controller
@ServerEndpoint(value = "/gobang/{username}/{chessboard_num}",encoders = WebSocketChessboardEncoder.class)
public class GobangSocketController {

    private static RedisUtil redisUtil;

    @Autowired
    public void setRedisUtil(RedisUtil redisUtil) {
        GobangSocketController.redisUtil=redisUtil;
    }
    @OnOpen
    public void openSession(@PathParam("username") String username, @PathParam("chessboard_num") String chessboard_num, Session session){
        //存储用户
        WebSocketUtil.USERS_ONLINE.put(username, session);
        ChessBoard chessBoard;
        // 判断WebSocketUtil是否已有chessboard
        if(WebSocketUtil.CHESSBOARD_ONLINE.containsKey(chessboard_num)){
            chessBoard=WebSocketUtil.CHESSBOARD_ONLINE.get(chessboard_num);
            //是否加入成功
            boolean join_success= chessBoard.join(username);
            // 没加入成功关闭来连接
        }else{
            //WebSocketUtil没有chessboard就去redis查找
            if(redisUtil.hasKey(chessboard_num)){
                chessBoard=(ChessBoard) redisUtil.get(chessboard_num);
                chessBoard.join(username);
            }else{
                //redis中没有chessboard,创建新的chessboard
                chessBoard=new ChessBoard();
                chessBoard.join(username);
                redisUtil.set(chessboard_num,chessBoard);
            }
            //存入WebSocketUtil中
            WebSocketUtil.CHESSBOARD_ONLINE.put(chessboard_num,chessBoard);
        }

        Set<String> usernames_set=chessBoard.getUsernames_set();
        //向所有在线用户发送用户上线通知消息
        String message = "["+username+"]进入聊天室";
        System.out.println(message);
        WebSocketUtil.sendMessageToUsers(usernames_set, ResponseData.success(message));
    }
    @OnClose
    public void closeSession(@PathParam("username") String username, @PathParam("chessboard_num") String chessboard_num,Session session){
        //删除用户
        WebSocketUtil.USERS_ONLINE.remove(username);
        String message = "["+username+"]离开了聊天室";
        // chessboard删除用户
        if(WebSocketUtil.CHESSBOARD_ONLINE.containsKey(chessboard_num)){
            ChessBoard chessBoard=WebSocketUtil.CHESSBOARD_ONLINE.get(chessboard_num);
            int onlineNum= chessBoard.leave(username);
            if(onlineNum==0){
                WebSocketUtil.CHESSBOARD_ONLINE.remove(chessboard_num);
            }
            //向所有在线用户发送用户下线通知消息
            WebSocketUtil.sendMessageToUsers(chessBoard.getUsernames_set(),message);
        }
        //下线后关闭session
        try {
            System.out.println(message);
            session.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @OnMessage
    public void onMessage(@PathParam("username") String username,@PathParam("chessboard_num") String chessboard_num,String message){
        //向聊天室中的人发送消息
        ChessBoard chessBoard = WebSocketUtil.CHESSBOARD_ONLINE.get(chessboard_num);
        message = "["+username+"]：" + message;
        System.out.println(message);
        WebSocketUtil.sendMessageToUsers(chessBoard.getUsernames_set(),message);
    }

    @OnError
    public void sessionError(Session session, Throwable throwable){
        try {
            session.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("WebSocket连接发生异常，message:"+throwable.getMessage());
    }
}
