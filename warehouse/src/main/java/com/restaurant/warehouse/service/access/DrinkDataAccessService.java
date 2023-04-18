package com.restaurant.warehouse.service.access;

import com.restaurant.warehouse.dao.DrinkDao;
import com.restaurant.warehouse.model.Drink;
import com.restaurant.warehouse.util.DrinkRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class DrinkDataAccessService implements DrinkDao {

    private final JdbcTemplate jdbcTemplate;
    private final DrinkRowMapper drinkRowMapper;

    public DrinkDataAccessService(JdbcTemplate jdbcTemplate, DrinkRowMapper drinkRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.drinkRowMapper = drinkRowMapper;
    }

    @Override
    public List<Drink> selectDrinks(int offset, int limit) {
        final String sql = """
                SELECT id, name, quantity, timestamp
                FROM drinks
                ORDER BY id
                OFFSET ?
                LIMIT ?
                """;
        return jdbcTemplate.query(sql, drinkRowMapper, offset, limit);
    }

    @Override
    public int insertDrink(Drink food) {
        final String sql = """
                INSERT INTO drinks (name, quantity, timestamp)
                VALUES (?, ?, ?);
                 """;
        return jdbcTemplate.update(
                sql,
                food.getName(), food.getQuantity(), food.getTimestamp()
        );
    }

    @Override
    public int updateDrink(long id, Drink drink) {
        final String sql = """
                UPDATE drinks
                SET name = ?, quantity = ?
                WHERE id = ?;
                 """;
        return jdbcTemplate.update(
                sql,
                drink.getName(), drink.getQuantity(), id
        );
    }

    @Override
    public int deleteDrink(Long id) {
        final String sql = """
                DELETE FROM drinks   
                WHERE id = ?
                """;
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public Optional<Drink> selectDrinkById(long id) {
        final String sql = """
                SELECT id, name, quantity, timestamp
                FROM drinks
                WHERE id = ?
                 """;
        return jdbcTemplate.query(sql, drinkRowMapper, id)
                .stream()
                .findFirst();
    }

    @Override
    public Optional<Drink> selectDrinkByName(String name) {
        final String sql = """
                SELECT id, name, quantity, timestamp
                FROM drinks
                WHERE name = ?
                 """;
        return jdbcTemplate.query(sql, drinkRowMapper, name)
                .stream()
                .findFirst();
    }

}