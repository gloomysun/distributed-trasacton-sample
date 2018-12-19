package com.ly.account.entity;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity(name="tb_account")
public class Account {
    @Id
    private Long userId;
    private Long point; //积分

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getPoint() {
        return point;
    }

    public void setPoint(Long point) {
        this.point = point;
    }
}
