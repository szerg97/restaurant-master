package com.restaurant.apigateway.proxy;

import com.restaurant.apigateway.controller.dto.DrinkResponse;
import com.restaurant.apigateway.controller.dto.FoodResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(url = "${warehouse.url}", name = "warehouse")
public interface WarehouseServiceProxy {
    @GetMapping("/api/v1/warehouse/foods")
    List<FoodResponse> getFoods(@RequestParam(name = "offset", required = false) Integer offset,
                                @RequestParam(name = "limit", required = false) Integer limit);
    @GetMapping("/api/v1/warehouse/drinks")
    List<DrinkResponse> getDrinks(@RequestParam(name = "offset", required = false) Integer offset,
                                  @RequestParam(name = "limit", required = false) Integer limit);
}
