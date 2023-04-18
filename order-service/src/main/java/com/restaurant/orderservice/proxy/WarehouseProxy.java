package com.restaurant.orderservice.proxy;

import com.restaurant.orderservice.controller.dto.CheckedFoodsRequest;
import com.restaurant.orderservice.controller.dto.CheckedFoodsResponse;
import com.restaurant.orderservice.controller.dto.OrderedFoodsRequest;
import com.restaurant.orderservice.controller.dto.OrderedFoodsResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(url = "${warehouse.base-url}", name = "warehouse")
public interface WarehouseProxy {
    @PostMapping("/foods/order")
    OrderedFoodsResponse orderFoods(@RequestBody OrderedFoodsRequest request);
    @PostMapping("/foods/check")
    CheckedFoodsResponse checkFoods(@RequestBody CheckedFoodsRequest request);
}
