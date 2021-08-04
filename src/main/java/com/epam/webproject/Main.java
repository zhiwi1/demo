package com.epam.webproject;

import com.epam.webproject.exception.DaoException;

import com.epam.webproject.model.dao.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

class Main {
    private  static  final Logger logger = LogManager.getLogger();

    public static void main(String args[]) throws DaoException {
        UserDao dao=DaoProvider.getInstance().getUserDao();
        dao.findInfoForRates("login");
    }
}