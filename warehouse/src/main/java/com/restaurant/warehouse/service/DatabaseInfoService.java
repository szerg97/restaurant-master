package com.restaurant.warehouse.service;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class DatabaseInfoService {

    @Autowired
    private DataSource dataSource;

    public List<DatabaseTable> getTables(String schema) {
        List<DatabaseTable> tables = new ArrayList<>();
        try(Connection connection = dataSource.getConnection()){
            ResultSet resultSet = connection.getMetaData().getTables(null, schema, "%", new String[]{"TABLE","VIEW"});
            while(resultSet.next()){
                DatabaseTable table = new DatabaseTable();
                table.setSchema(schema);
                table.setName(resultSet.getString("TABLE_NAME"));
                table.setType(DatabaseTable.TableType.valueOf(resultSet.getString("TABLE_TYPE")));
                tables.add(table);
            }

        } catch (SQLException e) {
            throw new IllegalStateException(e + "There was some trouble during getting the tables");
        }
        return tables;
    }

    @Data
    public static class DatabaseTable{

        private String schema;
        private String name;
        private TableType type;

        public enum TableType{
            TABLE, VIEW
        }
    }
}
