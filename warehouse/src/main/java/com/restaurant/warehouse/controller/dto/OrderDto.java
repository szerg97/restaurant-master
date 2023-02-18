package com.restaurant.warehouse.controller.dto;

import com.restaurant.warehouse.model.Food;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
public class OrderDto implements Serializable {

    private List<FoodDto> foods;
}
