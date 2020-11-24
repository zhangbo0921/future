package com.qinghe.future.controller;

import com.qinghe.future.commons.result.Result;
import com.qinghe.future.commons.result.ResultType;
import com.qinghe.future.socket.WebSocketIm;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/message")
public class MessageController {

    @Resource
    WebSocketIm socketIm;

    @GetMapping("/send")
    public Result sendMessage(String name,String phone,String type) throws Exception {
        if(type.equals("1")){
            socketIm.send(name+"_"+phone,"{\"uuid\":\"f012a0d5-bb81-42c4-9ea2-3eb23d6e9804%40192.168.0.226\",\"call_hash\":\"18192283698\",\"leg\":\"bleg\",\"offer\":\"kxt-cc\",\"content\":\"你好请问有什么可以帮您？\",\"date\":\"2020-11-04 17:03:55\",\"timestamp\":\"1604480635729\",\"msg\":\"?°???± è???????????-é??è??\",\"seat_id\":\"jj002\"}");
        }else{
            socketIm.send(name+"_"+phone,"{\"uuid\":\"f012a0d5-bb81-42c4-9ea2-3eb23d6e9804%40192.168.0.226\",\"call_hash\":\"18192283698\",\"leg\":\"aleg\",\"offer\":\"kxt-cc\",\"content\":\"你好，我想投诉我们家楼下的KTV，他们声音太大，噪音扰民！！\",\"date\":\"2020-11-04 17:03:57\",\"timestamp\":\"1604480637241\",\"msg\":\"?°???± è???????????-é??è??\",\"seat_id\":\"jj002\"}");
        }
        return new Result(ResultType.SUCCESS);
    }


    @GetMapping("/getMsg")
    public Result getMsg() {
        return new Result(ResultType.SUCCESS);
    }

}
