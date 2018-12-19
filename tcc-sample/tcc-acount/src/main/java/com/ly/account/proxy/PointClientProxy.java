package com.ly.account.proxy;

import com.ly.account.client.PointClient;
import com.ly.account.entity.User;
import com.ly.point.entity.Point;
import common.base.ResponseResult;
import common.pojo.TransactionEntity;
import org.mengyun.tcctransaction.api.Compensable;
import org.mengyun.tcctransaction.api.Propagation;
import org.mengyun.tcctransaction.api.TransactionContext;
import org.mengyun.tcctransaction.context.MethodTransactionContextEditor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PointClientProxy {
    @Autowired
    private PointClient pointClient;

    @Compensable(propagation = Propagation.SUPPORTS, confirmMethod = "create", cancelMethod = "create", transactionContextEditor = MethodTransactionContextEditor.class)
    public ResponseResult<Point> create(TransactionContext transactionContext, User user) {
        return pointClient.create(new TransactionEntity<>(transactionContext, user));
    }
}
