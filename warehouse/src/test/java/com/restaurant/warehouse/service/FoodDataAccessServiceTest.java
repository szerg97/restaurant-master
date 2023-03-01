package com.restaurant.warehouse.service;

import com.restaurant.warehouse.AbstractTestcontainers;
import com.restaurant.warehouse.model.Food;
import com.restaurant.warehouse.util.FoodRowMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class FoodDataAccessServiceTest extends AbstractTestcontainers {

    private FoodDataAccessService underTest;
    private final FoodRowMapper foodRowMapper = new FoodRowMapper();

    @BeforeEach
    void setUp() {
        underTest = new FoodDataAccessService(
                getJdbcTemplate(),
                foodRowMapper
        );
    }

    @Test
    void itShouldSelectFoods() {
        //Given
        //When
        List<Food> actual = underTest.selectFoods(10);

        //Then
        assertThat(actual).isNotEmpty();
    }

    @Test
    void itShouldInsertFood() {
        //Given
        Food food = new Food(
                10001L,
                "Food 10001",
                LocalDateTime.now()
        );

        //When
        int count = underTest.insertFood(food);

        //Then
        assertThat(count).isEqualTo(1);
    }

    @Test
    void itShouldDeleteFood() {
        //Given
        long id = 1L;

        //When
        int count = underTest.deleteFood(id);

        //Then
        assertThat(count).isEqualTo(1);
    }

    @Test
    void itShouldSelectFoodById() {
        //Given
        long id = 1L;

        //When
        Optional<Food> food = underTest.selectFoodById(id);

        //Then
        assertThat(food).isPresent().hasValueSatisfying( f -> {
            assertThat(f.getId()).isEqualTo(id);
        });
    }
}