package com.qinghe.future.socket;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qinghe.future.analysis.service.OrderTypeAnalysis;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
@ServerEndpoint(value = "/im/{username}")
public class WebSocketIm {

    /**
     * 以用户的姓名为key，WebSocket为对象保存起来
     */
    private static Map<String, Session> clients = new ConcurrentHashMap<String, Session>();
    private static Map<String, Map<String,Object>> data = new HashMap<>();
    /**
     * 会话
     */
    private Session session;
    /**
     * 用户名称
     */
    private String username;

    @OnOpen
    public void onOpen(@PathParam("username") String username, Session session) {
        this.username = username;
        this.session = session;
        clients.put(username,session);
        data.put(username,new HashMap<>());
        log.info("坐席{},接入，当前人数{}人",username,clients.keySet().size());
    }

    @OnError
    public void onError(Session session, Throwable error) {
        log.info("服务端发生了错误" + error.getMessage());
    }

    /**
     * 连接关闭
     */
    @OnClose
    public void onClose() {
        clients.remove(username);
        data.remove(username);
        log.info(username + "断开链接！ 当前在线人数" + clients.keySet().size());
    }

    /**
     * 收到客户端的消息
     *
     * @param message 消息
     * @param session 会话
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        try {
            log.info("来自客户端消息：" + message);

        } catch (Exception e) {
            log.info("发生了错误了");
        }

    }

    public void send(String name,String msg) throws IOException {
        Map<String,Object> dataMap = data.get(name);
        //获取本次发送信息的分析数据
        JSONObject msgJson = JSONObject.parseObject(msg);
        if(dataMap!=null){
            if(msgJson.getJSONArray("analysis")!=null){
                JSONArray jsonArray = msgJson.getJSONArray("analysis");
                JSONObject jsonObject = jsonArray.getJSONObject(0);
                String type = jsonObject.getString("type");
                String value = jsonObject.getString("value");

                if (type.equals("工单类型")) {
                    Integer newScore = OrderTypeAnalysis.getScore(value);
                    Integer oldScore = 0;
                    //获取工单类型分析数据
                    Object orderType = dataMap.get("orderType");
                    if(orderType!=null){
                        //获取工单类型
                        oldScore = OrderTypeAnalysis.getScore(orderType.toString());
                    }

                    if(newScore<=oldScore){
                        msgJson.remove("analysis");
                    }else {
                        dataMap.put("orderType",value);
                    }
                }
            }
        }
        if(clients.get(name)!=null){
            clients.get(name).getBasicRemote().sendText(msgJson.toJSONString());
        }
    }
}
