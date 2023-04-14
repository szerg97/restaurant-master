package com.restaurant.warehouse.service;

import com.restaurant.warehouse.dao.FoodDao;
import com.restaurant.warehouse.model.Food;
import com.restaurant.warehouse.util.FoodRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class FoodDataAccessService implements FoodDao {

    private final JdbcTemplate jdbcTemplate;
    private final FoodRowMapper foodRowMapper;

    public FoodDataAccessService(JdbcTemplate jdbcTemplate, FoodRowMapper foodRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.foodRowMapper = foodRowMapper;
    }

    @Override
    public List<Food> selectFoods(int offset, int limit) {
        var sql = """
                SELECT id, name, quantity, timestamp
                FROM foods
                OFFSET ?
                LIMIT ?
                """;
        return jdbcTemplate.query(sql, foodRowMapper, offset, limit);
    }

    @Override
    public int insertFood(Food food) {
        var sql = """
                INSERT INTO foods(name, quantity, timestamp)
                VALUES (?, ?, ?);
                 """;
        return jdbcTemplate.update(
                sql,
                food.getName(), food.getQuantity(), food.getTimestamp()
        );
    }

    @Override
    public int updateFood(long id, Food food) {
        var sql = """
                UPDATE foods
                SET name = ?, quantity = ?
                WHERE id = ?;
                 """;
        return jdbcTemplate.update(
                sql,
                food.getName(), food.getQuantity(), id
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
                SELECT id, name, quantity, timestamp
                FROM foods
                WHERE id = ?
                 """;
        return jdbcTemplate.query(sql, foodRowMapper, id)
                .stream()
                .findFirst();
    }

    @Override
    public Optional<Food> selectFoodByName(String name) {
        var sql = """
                SELECT id, name, quantity, timestamp
                FROM foods
                WHERE name = ?
                 """;
        return jdbcTemplate.query(sql, foodRowMapper, name)
                .stream()
                .findFirst();
    }

}