package com.ly.user.scheduled;

import com.ly.user.entity.Message;
import com.ly.user.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class MessageScheduled {
    @Autowired
    private MessageService messageService;
    @Autowired
    private Environment env;

    /**
     * 定时发送消费失败的消息
     */
    @Scheduled(cron = "0 */1 * * * ?")
    public void handleTimeOutMessage() {
        log.info("【定时任务】开始重发失败消息");
        //查找需要重新发送的消息
        List<Message> messageList = messageService.filterMessage();
        Integer maxRetryCount = Integer.valueOf(env.getProperty("message.resend.maxTimes"));
        for (Message m : messageList) {
            log.info("【定时任务】开始重新发送消息：messageId:{},消息内容{}", m.getId(), m.getPayload());
            if (m.getRetryCount() > maxRetryCount) {
                //将消息标记为死亡
                log.info("【定时任务】消息已超过最大重发次数：messageId:{},消息内容{}", m.getId(), m.getPayload());
                messageService.markMessageDead(m);
                continue;
            }
            log.info("【定时任务】重新发送消息成功：messageId:{},消息内容{}", m.getId(), m.getPayload());
            messageService.reSendMessage(m);
        }

    }
}
