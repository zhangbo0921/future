package com.qinghe.future.config;

import com.qinghe.future.commons.utils.StringUtils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Data
@Slf4j
@Configuration
@ConfigurationProperties(prefix = "rocketmq.producer")
public class MQProducerConfiguration {

    private String groupName;
    private String namesrvAddr;
    private Integer maxMessageSize;
    private Integer sendMsgTimeout;
    private Integer retryTimesWhenSendFailed;

    @Bean
    public DefaultMQProducer defaultMQProducer (){

        if (StringUtils.isEmpty(groupName)) {
            throw new RuntimeException("groupName is null!!!");
        }
        if (StringUtils.isEmpty(namesrvAddr)) {
            throw new RuntimeException("namesrvAddr is null!!!");
        }

        DefaultMQProducer producer = new DefaultMQProducer(groupName);
        producer.setSendMsgTimeout(3000);
        producer.setNamesrvAddr(namesrvAddr);
        producer.setVipChannelEnabled(false);

        if(this.maxMessageSize!=null){
            producer.setMaxMessageSize(this.maxMessageSize);
        }
        if(this.sendMsgTimeout!=null){
            producer.setSendMsgTimeout(this.sendMsgTimeout);
        }
        //如果发送消息失败，设置重试次数，默认为2次
        if(this.retryTimesWhenSendFailed!=null){
            producer.setRetryTimesWhenSendFailed(this.retryTimesWhenSendFailed);
        }

        try {
            producer.start();
            log.info(String.format("producer is start ! groupName:[%s],namesrvAddr:[%s]", this.groupName, this.namesrvAddr));
        } catch (MQClientException e) {
            log.error(String.format("producer is error {}", e.getMessage(),e));
            throw new RuntimeException(e);
        }
        return producer;
    }
}
