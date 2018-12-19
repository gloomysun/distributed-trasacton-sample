package com.ly.account.service.impl;

import com.ly.account.client.PointClient;
import com.ly.account.entity.User;
import com.ly.account.mapper.UserMapper;
import com.ly.account.proxy.PointClientProxy;
import com.ly.account.service.UserService;
import com.ly.point.entity.Point;
import common.base.MsgCode;
import common.base.ResponseResult;
import common.exception.TccException;
import lombok.extern.slf4j.Slf4j;
import org.mengyun.tcctransaction.api.Compensable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PointClient pointClient;

    @Autowired
    private PointClientProxy pointClientProxy;

    @Transactional(rollbackFor = Exception.class)
    @Compensable(confirmMethod = "confirmRegister", cancelMethod = "cancelRegister")
    public User register(User user) {
        if (user == null) {
            throw new TccException(MsgCode.CHECK_USER);
        }
        user.setCreateTime(new Date());
        user.setModifiedTime(user.getCreateTime());
        userMapper.insertSelective(user);

        //注册积分账户
        ResponseResult<Point> pointResponseResult = pointClientProxy.create(null, user);
        if (!pointResponseResult.isSuccess()) {
            throw new TccException(MsgCode.ERROR);
        }

        int a = 1 / 0;
        //注册资金账户
        //TODO
        return user;

    }

    @Transactional(rollbackFor = Exception.class)
    public User confirmRegister(User user) {
        log.info("开始确认用户【{}】注册", user.getUsername());
        return user;
    }

    @Transactional(rollbackFor = Exception.class)
    public User cancelRegister(User user) {
        log.info("开始取消用户【{}】注册", user.getUsername());
        if (user.getId() == null) {
            //还未插入数据库
            return user;
        }
        User exist = userMapper.selectByPrimaryKey(user.getId());
        if (exist != null) {
            userMapper.delete(exist);
        }
        return exist;
    }
}
