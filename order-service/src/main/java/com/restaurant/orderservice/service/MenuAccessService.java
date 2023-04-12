package com.restaurant.orderservice.service;

import com.restaurant.orderservice.dao.MenuDao;
import com.restaurant.orderservice.model.Menu;
import com.restaurant.orderservice.util.MenuRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MenuAccessService implements MenuDao {

    private final JdbcTemplate jdbcTemplate;
    private final MenuRowMapper rowMapper;

    public MenuAccessService(JdbcTemplate jdbcTemplate, MenuRowMapper rowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
    }

    @Override
    public List<Menu> selectMenus(int offset, int limit) {
        String sql = """
                SELECT id, name, order_id, timestamp
                FROM menus
                OFFSET ?
                LIMIT ?
                """;
        return jdbcTemplate.query(sql, rowMapper, offset, limit);
    }

    @Override
    public int insertMenu(Menu food) {
        return 0;
    }

    @Override
    public int deleteMenu(Long id) {
        return 0;
    }

    @Override
    public Optional<Menu> selectMenuById(long id) {
        return Optional.empty();
    }

    @Override
    public Optional<Menu> selectMenuByName(String name) {
        return Optional.empty();
    }
}
