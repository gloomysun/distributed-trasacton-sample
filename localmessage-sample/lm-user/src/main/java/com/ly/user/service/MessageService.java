package com.ly.user.service;

import com.ly.common.base.Constants;
import com.ly.common.enums.MessageSendStatusEnum;
import com.ly.common.utils.IdWorker;
import com.ly.common.utils.JsonUtil;
import com.ly.user.entity.Message;
import com.ly.user.repository.MessageRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


@Service
public class MessageService {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private IdWorker idWorker;
    @Autowired
    private Environment env;

    @Transactional
    public void saveAndSend(Message message) {
        message.setStatus(MessageSendStatusEnum.SENDING.name());
        message.setCreateTime(new Date());
        message.setLastSendTime(new Date());
        message.setRetryCount(0);
        message.setIsDead(false);
        messageRepository.save(message);
        rabbitTemplate.convertAndSend(Constants.LOCAL_REGISTRY_QUEUE, message.getPayload());
    }

    /**
     * 确认消息，将消息状态修改为Success
     *
     * @param messageId
     */
    @Transactional
    public void confirmMessage(Long messageId) {
        if (true) {
            throw new RuntimeException("something error!!!!!!!!!!!!!!!!!!!!");
        }
        messageRepository.updateStatus(messageId, MessageSendStatusEnum.SUCCESS.name());
    }

    /**
     * @return
     */
    public List<Message> filterMessage() {

        Long currentTimeMillis = System.currentTimeMillis();
        Long intervalTime = Long.valueOf(env.getProperty("message.interval.time"));
        Long needTime = currentTimeMillis - intervalTime * 1000;
        List<Message> messageList = messageRepository.findByStatusAndLastSendTimeLessThanAndIsDead(MessageSendStatusEnum.SENDING.name(), new Date(needTime), false);
        return messageList;
    }

    /**
     * 将消息标记为死亡
     */
    @Transactional
    public void markMessageDead(Message message) {
        message.setIsDead(true);
        message.setStatus(MessageSendStatusEnum.FAILED.name());
        messageRepository.save(message);
//        messageRepository.markMessageDead(id);
    }

    /**
     * 重新发送消息
     *
     * @param m
     */
    @Transactional
    public void reSendMessage(Message m) {
        m.setRetryCount(m.getRetryCount() + 1);
        m.setLastSendTime(new Date());
        messageRepository.save(m);
        rabbitTemplate.convertAndSend(Constants.LOCAL_REGISTRY_QUEUE, JsonUtil.object2Json(m.getPayload()));
    }
}
