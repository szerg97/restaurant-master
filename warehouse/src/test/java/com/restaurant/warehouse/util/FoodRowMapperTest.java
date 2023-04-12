package com.restaurant.warehouse.util;

import com.restaurant.warehouse.model.Food;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class FoodRowMapperTest {

    @Test
    void itShouldMapRow() throws SQLException {
        //Given
        FoodRowMapper foodRowMapper = new FoodRowMapper();

        ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.getLong("id")).thenReturn(1L);
        when(resultSet.getString("name")).thenReturn("Food 1");
        LocalDateTime now = LocalDateTime.now();
        when(resultSet.getTimestamp("timestamp")).thenReturn(Timestamp.valueOf(now));

        //When
        Food actual = foodRowMapper.mapRow(resultSet, 1);

        //Then
        Food expected = new Food(1L, "Food 1", 10, Timestamp.valueOf(now));
        assertThat(actual).isEqualTo(expected);
    }
}