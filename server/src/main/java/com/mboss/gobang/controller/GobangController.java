package com.mboss.gobang.controller;
import com.mboss.gobang.entity.ChessBoard;
import com.mboss.gobang.entity.ResponseData;
import com.mboss.gobang.service.ChessBoardService;
import com.mboss.gobang.util.ObjectToMapUtils;
import com.mboss.gobang.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/gobang")
public class GobangController {
    @Autowired
    RedisUtil redisUtil;

    @Autowired
    ChessBoardService chessBoardService;

    @GetMapping("/createChessboard/{username}/{isXianShou}")
    public ResponseData createChessboard(@PathVariable("username") String username,@PathVariable("isXianShou") boolean isXianShou){
        ChessBoard chessBoard=new ChessBoard();
        String chessboard_num=chessBoard.getChessboard_num();
        if(isXianShou){
            chessBoard.join(username,true);
        }else{
            chessBoard.join(username,false);
        }
        chessBoardService.createChessBoardToRedis(chessboard_num,chessBoard);
        Map<String,String> res=new HashMap<>();
        res.put("chessboard_num",chessboard_num);
        return ResponseData.success(res);
    }
    @GetMapping("/isChessboard/{chessboard_num}")
    public ResponseData isChessboard(@PathVariable String chessboard_num){
        boolean isChessboard=redisUtil.hasKey(chessboard_num);
        Map<String,Object> res=new HashMap<>();
        res.put("isChessboard",isChessboard);
        return ResponseData.success(res);
    }
}
