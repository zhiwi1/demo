package com.epam.webproject;

import com.epam.webproject.exception.DaoException;

import com.epam.webproject.localization.Localization;
import com.epam.webproject.localization.LocalizationKey;
import com.epam.webproject.model.dao.*;
import com.epam.webproject.model.dao.impl.CommentDaoImpl;
import com.epam.webproject.model.dao.impl.TaskDaoImpl;
import com.epam.webproject.model.dao.impl.UserDaoImpl;
import com.epam.webproject.model.email.MailSender;
import com.epam.webproject.model.entity.RatesType;
import com.epam.webproject.model.entity.Role;
import com.epam.webproject.model.entity.Status;
import com.epam.webproject.model.entity.User;
import com.epam.webproject.util.PasswordEncryptor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Date;

class Main {
    private final static Logger logger = LogManager.getLogger();

    public static void main(String args[]) throws DaoException {
        Localization localization = Localization.valueOf("ru".toUpperCase());
        System.out.println(localization.getText(LocalizationKey.LOGIN_ERROR_MESSAGE));
        TaskDao dao = DaoProvider.getInstance().getTaskDao();
        dao.deleteTask("new task");
    }
}