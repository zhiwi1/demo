package com.epam.webproject.model.dao;

import com.epam.webproject.model.entity.User;
import com.epam.webproject.model.entity.RatesType;
import com.epam.webproject.model.entity.Role;
import com.epam.webproject.model.entity.Status;

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
        long id = resultSet.getInt(ID);
        String login = resultSet.getString(USER_LOGIN);
        String email = resultSet.getString(USER_EMAIL);
        int countOfSolve = resultSet.getInt(COUNT_OF_SOLVE);
        RatesType ratesOfSolve = RatesType.valueOf(resultSet.getString(RATES_OF_SOLVE));
        Role role = Role.valueOf(resultSet.getString(USER_ROLE));
        Status status = Status.valueOf(resultSet.getString(USER_STATUS));
        return createUser(id, login, email, countOfSolve, role, ratesOfSolve, status);
    }

}