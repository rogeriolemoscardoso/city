package com.distance.city.jdbc;

import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static com.distance.city.util.Constants.*;

@Component
public class ConnectionFactory {
    public Connection getConnection() throws SQLException {
        try {
            return DriverManager.getConnection(URL, USER,PASSWORD);
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }
    }
}
