package com.restaurant.apigateway.service;

import com.restaurant.apigateway.controller.dto.DrinkResponse;
import com.restaurant.apigateway.controller.dto.FoodResponse;
import com.restaurant.apigateway.controller.dto.OrderRequest;
import com.restaurant.apigateway.controller.dto.OrderResponse;
import com.restaurant.apigateway.proxy.OrderServiceProxy;
import com.restaurant.apigateway.proxy.WarehouseServiceProxy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WarehouseServiceExternal {

    private final WarehouseServiceProxy proxy;

    public WarehouseServiceExternal(WarehouseServiceProxy proxy) {
        this.proxy = proxy;
    }

    public List<FoodResponse> getFoods(Integer offset, Integer limit){
        return proxy.getFoods(offset, limit);
    }

    public List<DrinkResponse> getDrinks(Integer offset, Integer limit){
        return proxy.getDrinks(offset, limit);
    }
}
