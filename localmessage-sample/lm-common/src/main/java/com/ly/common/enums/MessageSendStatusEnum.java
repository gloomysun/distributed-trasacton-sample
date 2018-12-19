package com.ly.common.enums;

public enum  MessageSendStatusEnum {

    SENDING("发送中"),
    SUCCESS("发送成功"),
    FAILED("发送失败");

    private String desc;

    MessageSendStatusEnum(String desc) {
        this.desc = desc;
    }
}
