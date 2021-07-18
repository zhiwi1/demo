package com.epam.webproject.model.factory;

import com.epam.webproject.model.entity.type.RatesType;
import com.epam.webproject.model.entity.type.Role;
import com.epam.webproject.model.entity.User;

public class EntityFactory {
    private static EntityFactory instance;


    private EntityFactory() {
    }

    public static EntityFactory getInstance() {
        if (instance == null) {
            instance = new EntityFactory();
        }
        return instance;
    }

//    public User createUser(long id, String login, String email, int countOfSolve, Role roleType, RatesType ratesType) {
//        return new User(id, login, email, countOfSolve, roleType, ratesType);
//
//    }

}