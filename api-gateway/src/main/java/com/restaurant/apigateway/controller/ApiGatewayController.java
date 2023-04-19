package com.restaurant.apigateway.controller;

import com.restaurant.apigateway.controller.dto.*;
import com.restaurant.apigateway.service.OrderServiceExternal;
import com.restaurant.apigateway.service.WarehouseServiceExternal;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/restaurant")
public class ApiGatewayController {

    private final OrderServiceExternal orderServiceExternal;
    private final WarehouseServiceExternal warehouseServiceExternal;

    public ApiGatewayController(OrderServiceExternal orderServiceExternal, WarehouseServiceExternal warehouseServiceExternal) {
        this.orderServiceExternal = orderServiceExternal;
        this.warehouseServiceExternal = warehouseServiceExternal;
    }

    @GetMapping("/menu")
    public List<MenuResponse> getMenus(){
        return orderServiceExternal.getMenus();
    }

    @PostMapping("/order")
    public OrderResponse placeOrder(
            @RequestBody OrderRequest request){
        return orderServiceExternal.placeOrder(request);
    }

    @GetMapping("/foods")
    public List<FoodResponse> getFoods(
            @RequestParam(name = "offset", required = false) @Min(1) Integer offset,
            @RequestParam(name = "limit", required = false) @Min(1) @Max(100) Integer limit
    ){
        return warehouseServiceExternal.getFoods(offset, limit);
    }

    @GetMapping("/drinks")
    public List<DrinkResponse> getDrinks(
            @RequestParam(name = "offset", required = false) @Min(1) Integer offset,
            @RequestParam(name = "limit", required = false) @Min(1) @Max(100) Integer limit
    ){
        return warehouseServiceExternal.getDrinks(offset, limit);
    }
}
