package com.restaurant.orderservice.controller;

import com.restaurant.orderservice.controller.dto.Order;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderServiceController {

    @Value("${warehouse.url}")
    private String url;

    @GetMapping("/test")
    public String test(){
        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl;
        fooResourceUrl = "http://" + url + ":8081/api/v1/warehouse";
        ResponseEntity<String> response
                = restTemplate.getForEntity(fooResourceUrl + "/meal", String.class);
        return response.toString();
    }

    @PostMapping("/add")
    public String placeOrder(@RequestBody Order order){
        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl;
        fooResourceUrl = "http://" + url + ":8081/api/v1/warehouse";
        ResponseEntity<String> response
                = restTemplate.postForEntity(fooResourceUrl + "/serve", order, String.class);
        return response.toString();
    }
}
