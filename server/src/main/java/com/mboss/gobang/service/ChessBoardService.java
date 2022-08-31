package com.mboss.gobang.service;


import com.mboss.gobang.entity.ChessBoard;
import com.mboss.gobang.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChessBoardService {
    @Autowired
    RedisUtil redisUtil;
    public void createChessBoardToRedis(String chessboard_num, ChessBoard chessBoard){
        redisUtil.set(chessboard_num,chessBoard,60*60*24);
    }
}
