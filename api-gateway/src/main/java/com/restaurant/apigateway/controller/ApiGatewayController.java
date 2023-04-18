package com.restaurant.apigateway.controller;

import com.restaurant.apigateway.service.OrderServiceExternal;
import com.restaurant.apigateway.controller.dto.OrderRequest;
import com.restaurant.apigateway.controller.dto.OrderResponse;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/restaurant")
public class ApiGatewayController {

    private final OrderServiceExternal externalService;

    public ApiGatewayController(OrderServiceExternal externalService) {
        this.externalService = externalService;
    }

    @PostMapping("/order")
    @CrossOrigin(origins = "*")
    public OrderResponse placeOrder(
            @RequestBody OrderRequest request){
        return externalService.placeOrder(request);
    }
}
