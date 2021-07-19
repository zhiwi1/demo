package com.epam.webproject.model.dao;


import com.epam.webproject.model.dao.impl.UserDaoImpl;

public class DaoDefinition {
        private static DaoDefinition instance;

        private final UserDao userDao;

        private DaoDefinition() {
//            this.orderDao = new OrderDaoImpl();
//            this.postDao = new PostDaoImpl();
            this.userDao = new UserDaoImpl();
        }

        public static DaoDefinition getInstance() {
            if (instance == null) {
                instance = new DaoDefinition();
            }

            return instance;
        }


        public UserDao getUserDao() {
            return userDao;
        }
    }

