package com.epam.webproject.model.connection;


import java.sql.Connection;
import java.sql.SQLException;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ConnectionCreatorTest {

    @Test
    public void createConnectionTest() {
        try (Connection connection = ConnectionCreator.getConnection()) {
            Assert.assertNotNull(connection);
        } catch (SQLException e) {
            Assert.fail("Can not create connection", e.getCause());
        }
    }

}

