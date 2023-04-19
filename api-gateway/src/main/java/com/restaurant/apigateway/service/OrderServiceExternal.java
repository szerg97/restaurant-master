package com.restaurant.apigateway.service;

import com.restaurant.apigateway.controller.dto.MenuResponse;
import com.restaurant.apigateway.controller.dto.OrderRequest;
import com.restaurant.apigateway.controller.dto.OrderResponse;
import com.restaurant.apigateway.proxy.OrderServiceProxy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceExternal {

    private final OrderServiceProxy proxy;

    public OrderServiceExternal(OrderServiceProxy proxy) {
        this.proxy = proxy;
    }

    public OrderResponse placeOrder(OrderRequest request){
        return proxy.placeOrder(request);
    }
    public List<MenuResponse> getMenus(){
        return proxy.getMenus();
    }
}
