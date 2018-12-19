package com.ly.point.controller;

import com.ly.account.entity.User;
import com.ly.point.entity.Point;
import com.ly.point.service.PointService;
import common.base.ResponseResult;
import common.pojo.TransactionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/point")
public class PointController {

    @Autowired
    private PointService pointService;

    @PostMapping("/create")
    public ResponseResult<Point> create(@RequestBody TransactionEntity<User> userTransactionEntity) {
        return ResponseResult.success(pointService.create(userTransactionEntity.getTransactionContext(), userTransactionEntity.getBody()));
    }
}
