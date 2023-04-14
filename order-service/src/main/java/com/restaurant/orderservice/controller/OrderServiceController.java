package com.restaurant.orderservice.controller;

import com.restaurant.orderservice.controller.dto.FoodResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/v1/order-service")
public class OrderServiceController {

    @Value("${warehouse.url}")
    private String url;

    @GetMapping("/foods")
    public FoodResponse[] getFoods(){
        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl;
        fooResourceUrl = "http://" + url + ":8081/api/v1/warehouse";
        return restTemplate.getForEntity(fooResourceUrl + "/foods?limit=25", FoodResponse[].class).getBody();
    }
}
