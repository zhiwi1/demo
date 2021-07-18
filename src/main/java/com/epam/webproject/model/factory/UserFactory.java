package com.epam.webproject.model.factory;

import com.epam.webproject.model.entity.User;
import com.epam.webproject.model.entity.type.RatesType;
import com.epam.webproject.model.entity.type.Role;
import com.epam.webproject.model.entity.type.Status;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.epam.webproject.model.dao.DatabaseColumnName.*;

public class UserFactory {
    private static UserFactory instance;

    private UserFactory() {
    }

    public static UserFactory getInstance() {
        if (instance == null) {
            instance = new UserFactory();
        }
        return instance;
    }

    public User createUser(long id, String login, String email, int countOfSolve, Role roleType, RatesType ratesType, Status status) {
        return new User(id, login, email, countOfSolve, roleType, ratesType, status);
    }
    public User createUser(ResultSet resultSet) throws SQLException {
        long id = resultSet.getInt(USER_ID);
        String login = resultSet.getString(USER_LOGIN);
        String email = resultSet.getString(USER_EMAIL);
        int countOfSolve = resultSet.getInt(COUNT_OF_SOLVE);
        RatesType ratesOfSolve = RatesType.valueOf(resultSet.getString(RATES_OF_SOLVE));
        System.out.println( resultSet.getString(USER_ROLE));
        Role role = Role.valueOf(resultSet.getString(USER_ROLE));
        Status status = Status.valueOf(resultSet.getString(USER_STATUS));
return createUser(id, login, email, countOfSolve, role, ratesOfSolve,status);
    }

}
