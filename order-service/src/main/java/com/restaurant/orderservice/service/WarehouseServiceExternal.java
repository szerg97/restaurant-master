package com.restaurant.orderservice.service;

import com.restaurant.orderservice.controller.dto.CheckedFoodsRequest;
import com.restaurant.orderservice.controller.dto.CheckedFoodsResponse;
import com.restaurant.orderservice.controller.dto.OrderedFoodsRequest;
import com.restaurant.orderservice.controller.dto.OrderedFoodsResponse;
import com.restaurant.orderservice.proxy.WarehouseProxy;
import org.springframework.stereotype.Service;

@Service
public class WarehouseServiceExternal {

    private final WarehouseProxy proxy;

    public WarehouseServiceExternal(WarehouseProxy proxy) {
        this.proxy = proxy;
    }

    public OrderedFoodsResponse orderFoods(OrderedFoodsRequest request){
        return proxy.orderFoods(request);
    }

    public CheckedFoodsResponse checkFoods(CheckedFoodsRequest request){
        return proxy.checkFoods(request);
    }
}
