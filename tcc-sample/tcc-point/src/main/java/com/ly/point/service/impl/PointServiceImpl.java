package com.ly.point.service.impl;

import com.ly.account.entity.User;
import com.ly.point.entity.Point;
import com.ly.point.mapper.PointMapper;
import com.ly.point.service.PointService;
import lombok.extern.slf4j.Slf4j;
import org.mengyun.tcctransaction.api.Compensable;
import org.mengyun.tcctransaction.api.TransactionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class PointServiceImpl implements PointService {

    @Autowired
    private PointMapper pointMapper;

    @Transactional(rollbackFor = Exception.class)
    @Compensable(confirmMethod = "confirmCreate", cancelMethod = "cancelCreate", transactionContextEditor = Compensable.DefaultTransactionContextEditor.class)
    public Point create(TransactionContext transactionContext, User user) {
        Point point = new Point();
        point.setUserId(user.getId());
        point.setValue(100l);
        pointMapper.insertSelective(point);
        return point;
    }

    @Transactional(rollbackFor = Exception.class)
    public void confirmCreate(TransactionContext transactionContext, User user) {
        log.info("开始确认用户【{}】积分账户的创建", user.getUsername());
    }

    @Transactional(rollbackFor = Exception.class)
    public void cancelCreate(TransactionContext transactionContext, User user) {
        log.info("开始取消用户【{}】积分账户的创建", user.getUsername());
        Point point = pointMapper.selectByPrimaryKey(user.getId());
        if (point != null) {
            pointMapper.delete(point);
        }
    }
}