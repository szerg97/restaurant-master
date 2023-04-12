package com.restaurant.warehouse.controller;

import com.restaurant.warehouse.service.DatabaseInfoService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/warehouse")
public class WarehouseController {

    @Value("${spring.datasource.url}")
    private static String DATABASE_URL;
    private final DatabaseInfoService databaseInfoService;

    public WarehouseController(DatabaseInfoService databaseInfoService) {
        this.databaseInfoService = databaseInfoService;
    }

    @GetMapping("/tables")
    public List<DatabaseInfoService.DatabaseTable> getTables(){
        return databaseInfoService.getTables("public");
    }

    @GetMapping("/datasource")
    public String getDataSourceUrl(){
        return DATABASE_URL;
    }
}
