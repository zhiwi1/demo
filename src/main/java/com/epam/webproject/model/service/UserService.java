package com.epam.webproject.model.service;

import com.epam.webproject.exception.ServiceException;
import com.epam.webproject.model.entity.Task;
import com.epam.webproject.model.entity.User;

import java.util.List;
import java.util.Optional;


public interface UserService {
    public Feedback registerUser(String login, String email, String password, String confirmPassword) throws ServiceException;

    public boolean signInUser(String loginOrEmail, String password) throws ServiceException;

    public Optional<User> findByLoginOrEmail(String login) throws ServiceException;
    public List<User> showAllUsers() throws ServiceException;

}
