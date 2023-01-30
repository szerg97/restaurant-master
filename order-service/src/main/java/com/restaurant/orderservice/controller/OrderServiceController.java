package com.restaurant.orderservice.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderServiceController {

    @Value("${warehouse.url}")
    private String url;

    @PostMapping("/add")
    public String placeOrder(){
        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl;
        fooResourceUrl = "http://" + url + ":8081/api/v1/warehouse";
        ResponseEntity<String> response
                = restTemplate.getForEntity(fooResourceUrl + "/meal", String.class);
        return response.toString();
    }
}
