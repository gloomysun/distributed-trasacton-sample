package com.ly.account.service;

import com.ly.account.entity.Account;
import com.ly.account.repository.AccountRepository;
import com.ly.common.base.Constants;
import com.ly.common.domain.PayLoad;
import com.ly.common.utils.JsonUtil;
import com.ly.user.api.MessageApi;
import org.springframework.amqp.core.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private MessageApi messageApi;

    @Transactional
    public void addRegisterPoint(Long userId) {
        Account account = new Account();
        account.setUserId(userId);
        account.setPoint(Constants.REGISTER_POINT);
        accountRepository.save(account);
    }

    public Boolean existsUser(Long userId) {
        return accountRepository.findById(userId).isPresent();
    }

    @Transactional
    public void addRegisterPointAndConfirmMessage(Message message) {
        PayLoad payLoad = JsonUtil.json2Object(new String(message.getBody()), PayLoad.class);
        Long userId = payLoad.getUserid();
        Long messageId = payLoad.getMessageid();
        //幂等性验证,判断是否已处理该消息
        if (existsUser(userId)) {
            return;
        }
        //为用户添加积分
        addRegisterPoint(userId);
        //修改消息状态
        messageApi.confirmMessage(messageId);
    }
}
