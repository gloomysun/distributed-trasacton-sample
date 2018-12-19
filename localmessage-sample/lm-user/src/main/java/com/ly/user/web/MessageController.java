package com.ly.user.web;

import com.ly.common.base.ResponseResult;
import com.ly.user.api.MessageApi;
import com.ly.user.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/message")
public class MessageController implements MessageApi{

    @Autowired
    private MessageService messageService;

    @PostMapping("/confirm/{messageId}")
    public ResponseResult confirmMessage(@PathVariable("messageId") Long messageId) {
        messageService.confirmMessage(messageId);
        return ResponseResult.success();
    }
}
