package com.restaurant.apigateway.controller;

import com.restaurant.apigateway.service.OrderServiceExternal;
import com.restaurant.apigateway.controller.dto.OrderRequest;
import com.restaurant.apigateway.controller.dto.OrderResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/gateway")
public class ApiGatewayController {

    private final OrderServiceExternal externalService;

    public ApiGatewayController(OrderServiceExternal externalService) {
        this.externalService = externalService;
    }

    @PostMapping("/order")
    @ResponseBody
    @CrossOrigin(origins = "*")
    public OrderResponse placeOrder(
            @RequestBody OrderRequest request){
        return externalService.placeOrder(request);
    }
}
