package com.mboss.gobang.entity;

import com.mboss.gobang.util.RandomUtil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ChessBoard {
    /**
     *棋盘数组15*15
     * 0 没有棋子
     * 1 黑棋
     * 2 白棋
     */
    private int [][] chessboard_arr;

    /**
     * 先手用户名和后手用户名
     */
    private String username_xianshou,username_houshou;
    Set<String> usernames_set=new HashSet<>();

    /**
     * 记录步数
     */
    private List<int[]> steps;

    /**
     * 当前第几步
     * step_num%2==0 先手
     * step_num%2==1 后手
     */
    private int step_num;
    /**
     * 计时
     */
    private int play_time;

    //棋盘号
    private String chessboard_num;

    // 在线人数
    private int onlineNum;
    /**
     * 初始化
     */
    public ChessBoard(){
        chessboard_arr=new int[15][15];
        steps=new ArrayList<>();
        step_num=0;
        play_time=30;
        chessboard_num= RandomUtil.random8Num();
        username_xianshou=null;
        username_houshou=null;
        onlineNum=0;
    }
    public boolean join(String username){
        if(username_xianshou==null){
            return join(username,true);
        }
        if(username_houshou==null){
            return join(username,false);
        }
        return false;
    }
    public boolean join(String username,boolean isXianShou){
        if(onlineNum>=2){
            return false;
        }else{
            if(usernames_set.contains(username)) return true;
            if(isXianShou){
                if(username_xianshou==null){
                    username_xianshou=username;
                }else{
                    return false;
                }
            }else{
                if(username_houshou==null){
                    username_houshou=username;
                }else{
                    return false;
                }
            }
            usernames_set.add(username);
            onlineNum++;
            return true;
        }
    }

    public int leave(String username){
        if(usernames_set.contains(username)){
            usernames_set.remove(username);
            if(username_xianshou!=null&&username_xianshou.equals(username)){
                username_xianshou=null;
            }else if(username_houshou!=null&&username_houshou.equals(username)){
                username_houshou=null;
            }
            return --onlineNum;
        }
        return onlineNum;
    }

    public boolean isWin(int x,int y){
        return false;
    }
    public void play(String username,int x,int y){
        // 这个人是否能下
        if(!username.equals(whoIsNext())){
            throw new RuntimeException("此时不该你下"+username);
        }

        // 该位置是否能下
        if(chessboard_arr[x][y]==0){
            // 该先手or该后手
            if(step_num%2==0) {
                chessboard_arr[x][y] = 1;
            }else{
                chessboard_arr[x][y] = 2;
            }
            // 步数加一
            step_num++;
            steps.add(new int[]{x,y});
        }else{
            throw new RuntimeException("下的位置已经有棋子了");
        }
    }
    public String whoIsNext(){
        if(step_num%2==0){
            return username_xianshou;
        }else{
            return username_houshou;
        }

    }

    public int[][] getChessboard_arr() {
        return chessboard_arr;
    }

    public void setChessboard_arr(int[][] chessboard_arr) {
        this.chessboard_arr = chessboard_arr;
    }

    public String getUsername_xianshou() {
        return username_xianshou;
    }

    public void setUsername_xianshou(String username_xianshou) {
        this.username_xianshou = username_xianshou;
    }

    public String getUsername_houshou() {
        return username_houshou;
    }

    public void setUsername_houshou(String username_houshou) {
        this.username_houshou = username_houshou;
    }

    public List<int[]> getSteps() {
        return steps;
    }

    public void setSteps(List<int[]> steps) {
        this.steps = steps;
    }

    public int getStep_num() {
        return step_num;
    }

    public void setStep_num(int step_num) {
        this.step_num = step_num;
    }

    public int getPlay_time() {
        return play_time;
    }

    public void setPlay_time(int play_time) {
        this.play_time = play_time;
    }

    public String getChessboard_num() {
        return chessboard_num;
    }

    public void setChessboard_num(String chessboard_num) {
        this.chessboard_num = chessboard_num;
    }

    public int getOnlineNum() {
        return onlineNum;
    }

    public void setOnlineNum(int onlineNum) {
        this.onlineNum = onlineNum;
    }

    public Set<String> getUsernames_set() {
        return usernames_set;
    }

    public void setUsernames_set(Set<String> usernames_set) {
        this.usernames_set = usernames_set;
    }
}
