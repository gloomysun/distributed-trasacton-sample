package com.ly.point.api;

import com.ly.account.entity.User;
import com.ly.point.entity.Point;
import common.base.ResponseResult;
import common.pojo.TransactionEntity;
import org.mengyun.tcctransaction.api.Compensable;
import org.mengyun.tcctransaction.api.TransactionContext;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

public interface PointApi {
    @PostMapping(value = "/create",consumes = APPLICATION_JSON_VALUE)
    ResponseResult<Point> create(@RequestBody TransactionEntity<User> userTransactionEntity);
}
