package com.mboss.gobang.entity;

import java.util.HashMap;
import java.util.Map;

public class ResponseData<T> {
    private int code;

    private String msg;

    private T data;

    private final long timeStamp;

    public ResponseData(){
        timeStamp= System.currentTimeMillis();
    }

    public static <T> ResponseData success(T data){
        ResponseData responseData = new ResponseData();
        responseData.setCode(0);
        responseData.setMsg("success");
        responseData.setData(data);
        return  responseData;
    }

    /**
     *
     * @param returnCode
     * 40 未登录
     * @return
     * @param <T>
     */
    public static <T> ResponseData fail(int returnCode){
        ResponseData responseData=new ResponseData();
        responseData.setCode(returnCode);
        responseData.setMsg("fail");
        return responseData;
    }

    public static ResponseData stringFail(String msg){
        ResponseData responseData=new ResponseData();
        responseData.setCode(40);
        responseData.setMsg(msg);
        return responseData;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}