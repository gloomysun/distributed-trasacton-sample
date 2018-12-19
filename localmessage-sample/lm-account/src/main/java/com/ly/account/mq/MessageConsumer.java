package com.ly.account.mq;

import com.ly.account.service.AccountService;
import com.ly.common.base.Constants;
import com.ly.common.domain.PayLoad;
import com.ly.common.utils.JsonUtil;
import com.ly.user.api.MessageApi;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MessageConsumer {

    @Autowired
    private AccountService accountService;

    @Autowired
    private MessageApi messageApi;

    @RabbitListener(queues = Constants.LOCAL_REGISTRY_QUEUE)
    public void process(Message message, Channel channel) {
        try {
            log.info("【consumer】receive:{}", new String(message.getBody()));
            //处理该消息(1.幂等性判断，2.添加积分，3.确认消息)
            accountService.addRegisterPointAndConfirmMessage(message);
            //告诉服务器收到这条消息 已经被我消费了 可以在队列删掉 这样以后就不会再发了 否则消息服务器以为这条消息没处理掉 后续还会在发
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            e.printStackTrace();
            //丢弃这条消息
            // channel.basicNack(message.getMessageProperties().getDeliveryTag(), false,false);
            log.error("【consumer】receive: failed!!!!!!");
        }

    }
}
