package com.restaurant.warehouse.service.access;

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
        final String sql = """
                SELECT id, name, quantity, timestamp
                FROM foods
                ORDER BY id
                OFFSET ?
                LIMIT ?
                """;
        return jdbcTemplate.query(sql, foodRowMapper, offset, limit);
    }

    @Override
    public int insertFood(Food food) {
        final String sql = """
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
        final String sql = """
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
        final String sql = """
                DELETE FROM foods   
                WHERE id = ?
                """;
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public Optional<Food> selectFoodById(long id) {
        final String sql = """
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
        final String sql = """
                SELECT id, name, quantity, timestamp
                FROM foods
                WHERE name = ?
                 """;
        return jdbcTemplate.query(sql, foodRowMapper, name)
                .stream()
                .findFirst();
    }

}