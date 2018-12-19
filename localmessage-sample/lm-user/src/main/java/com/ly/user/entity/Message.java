package com.ly.user.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity(name = "tb_message")
@Data
public class Message {
    @Id
    private Long id;
    private String status;
    private String payload; //消息内容
    private Date createTime;
    private Integer retryCount; //重试次数
    private Boolean isDead;
    private Date lastSendTime;  //最后发送时间

}
