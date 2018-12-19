package com.ly.user.service;

import com.ly.common.domain.PayLoad;
import com.ly.common.utils.IdWorker;
import com.ly.common.utils.JsonUtil;
import com.ly.user.entity.Message;
import com.ly.user.entity.User;
import com.ly.user.repository.UserRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MessageService messageService;
    @Autowired
    private IdWorker idWorker;

    @Transactional
    public void saveAndSendMsg(User user) {
        //保存用户
        Long userid = idWorker.nextId();
        user.setId(userid);
        userRepository.save(user);

        Message message = registerMessage(user);
        //发送消息
        messageService.saveAndSend(message);
    }

    /**
     * 生成注册赠送积分的消息
     *
     * @param user 用户
     * @return Message
     */
    private Message registerMessage(User user) {
        Long messageId = idWorker.nextId();
        Message message = new Message();
        message.setId(messageId);
        PayLoad payLoad = new PayLoad(user.getId(),messageId);
        message.setPayload(JsonUtil.object2Json(payLoad));
        return message;
    }


}
