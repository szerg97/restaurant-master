package com.restaurant.warehouse.service;

import com.restaurant.warehouse.controller.dto.FoodRequest;
import com.restaurant.warehouse.controller.dto.FoodResponse;
import com.restaurant.warehouse.dao.FoodDao;
import com.restaurant.warehouse.model.Food;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class FoodServiceTest {

    @Mock
    private FoodDao foodDao;
    private FoodService underTest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        underTest = new FoodService(foodDao);
    }

    @Test
    void itShouldGetFoodsWithLimit() {
        //Given
        int limit = 10;

        //When
        underTest.getFoods(null, 10);

        //Then
        then(foodDao).should().selectFoods(1, limit);
    }

    @Test
    void itShouldGetFoodsWithoutLimit() {
        //Given
        Integer limit = null;

        //When
        underTest.getFoods(null, limit);

        //Then
        then(foodDao).should().selectFoods(1,50);
    }

    @Test
    void itShouldAddNewFood() {
        //Given
        Food food = new Food(1L, "Food 1", 10);
        given(foodDao.insertFood(food)).willReturn(1);
        //When
        underTest.addNewFood(new FoodRequest("Food 1", 10));

        //Then
        then(foodDao).should().insertFood(food);
    }

    @Test
    void itShouldNotAddNewFoodWhenAlreadyExistsThrow() {
        //Given
        Food food = new Food(1L, "Food 1", 10);
        given(foodDao.insertFood(food)).willReturn(0);
        //When
        //Then
        assertThatThrownBy(() -> underTest.addNewFood(new FoodRequest("Food 1", 10)))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("oops something went wrong");
    }

    @Test
    void itShouldDeleteFood() {
        //Given
        long id = 1L;
        given(foodDao.selectFoodById(id)).willReturn(Optional.of(new Food(1L, "Food 1", 10)));
        given(foodDao.deleteFood(id)).willReturn(1);

        //When
        underTest.deleteFood(id);

        //Then
        then(foodDao).should().deleteFood(id);
    }

    @Test
    void itShouldNotDeleteFood() {
        //Given
        long id = 1L;
        given(foodDao.selectFoodById(id)).willReturn(Optional.of(new Food(1L, "Food 1", 10)));
        given(foodDao.deleteFood(id)).willReturn(0);

        //When
        //Then
        assertThatThrownBy(() -> underTest.deleteFood(id))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("oops could not delete food");
    }

    @Test
    void itShouldThrowWhenDeleteFood() {
        //Given
        long id = 1L;
        given(foodDao.selectFoodById(id)).willReturn(Optional.empty());

        //When
        //Then
        assertThatThrownBy(() -> underTest.deleteFood(id))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining(String.format("Food with id %s not found", id));
    }

    @Test
    void itShouldGetFood() {
        //Given
        long id = 1;
        Food expected = new Food(1L, "Food 1", 10);
        given(foodDao.selectFoodById(id)).willReturn(Optional.of(expected));

        //When
        FoodResponse actual = underTest.getFood(id);

        //Then
        assertThat(Food.fromResponse(actual)).isEqualTo(expected);
    }

    @Test
    void itShouldThrowWhenGetFood() {
        //Given
        long id = 1;
        given(foodDao.selectFoodById(id)).willReturn(Optional.empty());

        //When
        //Then
        assertThatThrownBy(() -> underTest.getFood(id))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining(String.format("Food with id %s not found", id));
    }
}