package com.epam.webproject.model.service;

import com.epam.webproject.exception.ServiceException;
import com.epam.webproject.model.entity.User;

import java.util.Optional;



    public interface UserService {
        public Feedback registerUser(String login, String email, String password, String confirmPassword) throws ServiceException ;
        public boolean signInUser(String loginOrEmail, String password) throws ServiceException;

//
//        //UserDao
//        boolean logInUser(String email, String password) throws ServiceException;
//        boolean registerUser(String email, String password, String confirmPassword, String name,
//                             Optional<String> surname, int balance,
//                             UserRole role, UserStatus status) throws ServiceException;
//
//        boolean logOutUser(User user) throws ServiceException;
//    }

}
