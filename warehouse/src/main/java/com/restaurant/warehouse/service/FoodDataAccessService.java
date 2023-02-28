package com.restaurant.warehouse.service;

import com.restaurant.warehouse.data.FoodDao;
import com.restaurant.warehouse.model.Food;
import com.restaurant.warehouse.util.FoodRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class FoodDataAccessService implements FoodDao {

    private final JdbcTemplate jdbcTemplate;

    public FoodDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Food> selectFoods(int limit) {
        var sql = """
                SELECT id, name, timestamp
                FROM foods
                LIMIT ?
                """;
        return jdbcTemplate.query(sql, new FoodRowMapper(), limit);
    }

    @Override
    public int insertFood(Food food) {
        var sql = """
                INSERT INTO foods(id, name, timestamp)
                VALUES (?, ?, ?);
                 """;
        return jdbcTemplate.update(
                sql,
                food.getId(), food.getName(), food.getTimestamp()
        );
    }

    @Override
    public int deleteFood(Long id) {
        var sql = """
                DELETE FROM foods   
                WHERE id = ?
                """;
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public Optional<Food> selectFoodById(long id) {
        var sql = """
                SELECT id, name, timestamp
                FROM foods
                WHERE id = ?
                 """;
        return jdbcTemplate.query(sql, new FoodRowMapper(), id)
                .stream()
                .findFirst();
    }

}