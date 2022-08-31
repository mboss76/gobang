package com.mboss.gobang.entity;


import com.alibaba.fastjson.JSON;


import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

public class WebSocketChessboardEncoder implements Encoder.Text<ResponseData> {
    @Override
    public String encode(ResponseData responseData) {
        return JSON.toJSONString(responseData);
    }

    @Override
    public void init(EndpointConfig endpointConfig) {

    }

    @Override
    public void destroy() {

    }
}
