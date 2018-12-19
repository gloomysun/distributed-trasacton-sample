package com.ly.user.service;

import com.ly.user.entity.Message;
import com.sun.xml.internal.ws.server.sei.MessageFiller;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MessageServiceTest {

    @Autowired
    private MessageService messageService;

    @Test
    public void test(){
//        List<Message> messages = messageService.filterMessage();
//        System.out.println(messages.size());
    }
}