package com.distance.city;

import com.distance.city.jdbc.ConnectionFactory;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class ConnectionFactoryTest {

    @InjectMocks
    private ConnectionFactory connectionFactory;

    @Test
    public void testGetConnection() throws SQLException {
       assertNotNull(connectionFactory.getConnection());
    }
}

