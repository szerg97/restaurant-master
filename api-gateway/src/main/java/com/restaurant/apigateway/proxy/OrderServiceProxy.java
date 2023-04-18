package com.restaurant.apigateway.proxy;

import com.restaurant.apigateway.controller.dto.OrderRequest;
import com.restaurant.apigateway.controller.dto.OrderResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(url = "${order-service.url}", name = "order-service")
public interface OrderServiceProxy {
    @PostMapping("/api/v1/order-service/orders")
    OrderResponse placeOrder(@RequestBody OrderRequest request);
}
