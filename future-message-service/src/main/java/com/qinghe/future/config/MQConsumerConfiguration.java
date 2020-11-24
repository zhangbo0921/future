package com.qinghe.future.config;

import com.alibaba.fastjson.JSONObject;
import com.qinghe.future.analysis.AnalysisResult;
import com.qinghe.future.analysis.AnalysisService;
import com.qinghe.future.commons.utils.StringUtils;
import com.qinghe.future.socket.WebSocketIm;
import lombok.Data;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Data
@Slf4j
@Configuration
@ConfigurationProperties(prefix = "rocketmq.consume")
public class MQConsumerConfiguration {

    @Resource
    private WebSocketIm socketIm;
    @Resource
    private AnalysisService orderTypeAnalysis;
    @Resource
    private AnalysisService addrAnalysis;
    @Resource
    private AnalysisService timeAnalysis;

    private String groupName;
    private String namesrvAddr;
    private String topics;

    @Bean
    public DefaultMQPushConsumer defaultMQPushConsumer (){

        if (StringUtils.isEmpty(groupName)) {
            throw new RuntimeException("groupName is null!!!");
        }
        if (StringUtils.isEmpty(namesrvAddr)) {
            throw new RuntimeException("namesrvAddr is null!!!");
        }

        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer();
        consumer.setNamesrvAddr(namesrvAddr);
        consumer.setVipChannelEnabled(false);
        consumer.setConsumeMessageBatchMaxSize(1);
        consumer.setConsumerGroup(groupName);
        try {
            consumer.subscribe(topics,"*");
            consumer.registerMessageListener(new MessageListenerConcurrently() {
                @SneakyThrows
                @Override
                public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                    for (MessageExt msg : msgs) {
                        String message = new String(msg.getBody(), "UTF-8");
                        JSONObject messageJson = JSONObject.parseObject(message);
                        String seatId = messageJson.getString("seat_id");
                        String call_hash = messageJson.getString("call_hash");
                        List<AnalysisResult> result = analysisText(messageJson.getString("content"));
                        if(messageJson!=null){
                            messageJson.put("analysis",result);
                            socketIm.send(seatId+"_"+call_hash,messageJson.toJSONString());
                        }else{
                            socketIm.send(seatId+"_"+call_hash,message);
                        }
                        System.out.printf("%s Receive New Messages: %s %n", Thread.currentThread().getName(), messageJson.toJSONString());
                    }
                    // 标记该消息已经被成功消费
                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                }
            });
            consumer.start();
            log.info(String.format("consumer is start ! groupName:[%s],namesrvAddr:[%s],topics:[%s]"
                    , this.groupName, this.namesrvAddr,this.topics));
        } catch (MQClientException e) {
            log.error(String.format("consumer is error {}"
                    , e.getMessage(),e));
            throw new RuntimeException(e);
        }
        return consumer;
    }
    List<AnalysisResult> analysisText(String text){
        List<AnalysisResult> list = new ArrayList<>();
        AnalysisResult orderTypeResult = orderTypeAnalysis.analysisText(text);
        if(orderTypeResult!=null){
            list.add(orderTypeResult);
        }
//        AnalysisResult addrTesult = addrAnalysis.analysisText(text);
//        if(orderTypeResult!=null){
//            list.add(orderTypeResult);
//        }
//        AnalysisResult timeResult = timeAnalysis.analysisText(text);
//        if(orderTypeResult!=null){
//            list.add(orderTypeResult);
//        }
        return list.size()==0?null:list;
    }
}
