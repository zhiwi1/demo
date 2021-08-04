package com.epam.webproject.model.service;

import com.epam.webproject.exception.DaoException;
import com.epam.webproject.exception.ServiceException;
import com.epam.webproject.model.entity.RatesType;
import com.epam.webproject.model.entity.Status;
import com.epam.webproject.model.entity.Task;
import com.epam.webproject.model.entity.User;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.Optional;


public interface UserService {
    public Feedback registerUser(String login, String email, String password, String confirmPassword) throws ServiceException;

    public boolean signInUser(String loginOrEmail, String password) throws ServiceException;

    public Optional<User> findByLoginOrEmail(String login) throws ServiceException;

    public Deque<User> showAllUsers() throws ServiceException;

    public boolean updateUser(String newLogin, String newEmail, String oldLogin, String oldEmail) throws ServiceException;

    public Deque<User> findByFullText(String text) throws ServiceException;

    public boolean unblockUser(String login) throws ServiceException;

    public boolean blockUser(String login) throws ServiceException;

    public boolean checkUserStatus(String login, Status expectedStatus) throws ServiceException;

    public Optional<String> findLogin(String loginOrEmail) throws ServiceException;

    public boolean forgetPassword(String email) throws ServiceException;

    public RatesType calculateRatesOfSolve(String login) throws ServiceException;

    public boolean setRates(String login,RatesType ratesType) throws ServiceException;


    }
