package com.ly.point.service;

import com.ly.account.entity.User;
import com.ly.point.entity.Point;
import org.mengyun.tcctransaction.api.Compensable;
import org.mengyun.tcctransaction.api.TransactionContext;


public interface PointService {
    @Compensable
    Point create(TransactionContext transactionContext, User user);

    void confirmCreate(TransactionContext transactionContext, User user);

    void cancelCreate(TransactionContext transactionContext, User user);
}
