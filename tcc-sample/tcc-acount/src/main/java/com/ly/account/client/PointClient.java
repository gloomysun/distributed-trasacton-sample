package com.ly.account.client;

import com.ly.account.entity.User;
import com.ly.point.api.PointApi;
import com.ly.point.entity.Point;
import common.base.ResponseResult;
import common.pojo.TransactionEntity;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "tcc-point", path = "point")
public interface PointClient extends PointApi {
}
