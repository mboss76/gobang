package com.mboss.gobang.controller;
import com.mboss.gobang.entity.ChessBoard;
import com.mboss.gobang.entity.ResponseData;
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

    @GetMapping("/createChessboard/{username}/{isXianShou}")
    public ResponseData createChessboard(@PathVariable("username") String username,@PathVariable("isXianShou") boolean isXianShou){
        ChessBoard chessBoard=new ChessBoard();
        String chessboard_num=chessBoard.getChessboard_num();
        if(isXianShou){
            chessBoard.setUsername_xianshou(username);
        }else{
            chessBoard.setUsername_houshou(username);
        }
        redisUtil.set(chessboard_num,chessBoard,60*60*24);
        Map<String,String> res=new HashMap<>();
        res.put("chessboard_num",chessboard_num);
        return ResponseData.success(res);
    }
}
