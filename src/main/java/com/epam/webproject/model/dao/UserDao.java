package com.epam.webproject.model.dao;

import com.epam.webproject.exception.DaoException;
import com.epam.webproject.model.entity.RatesType;
import com.epam.webproject.model.entity.Role;
import com.epam.webproject.model.entity.Status;
import com.epam.webproject.model.entity.User;

import java.util.*;

/**
 * Interface for class with SQL requests to `users`
 */
public interface UserDao {


    /**
     * Create new user.
     *
     * @param user     the user
     * @param password the password
     * @param salt     the salt
     * @return true, if successfully created in db, false, if unsuccessfully
     * @throws DaoException the dao exception
     */
    public boolean createNewUser(User user, String password, String salt) throws DaoException;

    /**
     * Find user login data by login.
     *
     * @param login the login of user
     * @return the map with login data
     * @throws DaoException the dao exception
     */
    public Map<String, Optional<String>> findUserLoginDataByLogin(String login) throws DaoException;

    /**
     * Find user login data by email.
     *
     * @param email the email
     * @return the map with login data
     * @throws DaoException the dao exception
     */
    public Map<String, Optional<String>> findUserLoginDataByEmail(String email) throws DaoException;

    /**
     * Exist rows by email.
     *
     * @param email the email of user
     * @return the boolean
     * @throws DaoException the dao exception
     */
    public boolean existRowsByEmail(String email) throws DaoException;

    /**
     * Exist rows by login.
     *
     * @param login the login of user
     * @return the boolean
     * @throws DaoException the dao exception
     */
    public boolean existRowsByLogin(String login) throws DaoException;



    /**
     * Find by login optional.
     *
     * @param login the login
     * @return the optional
     * @throws DaoException the dao exception
     */
    public Optional<User> findByLogin(String login) throws DaoException;

    /**
     * Find by email optional.
     *
     * @param email the email
     * @return the optional
     * @throws DaoException the dao exception
     */
    public Optional<User> findByEmail(String email) throws DaoException;

    /**
     * Update username .
     *
     * @param newLogin the new login
     * @param newEmail the new email
     * @param oldLogin the old login
     * @return the boolean
     * @throws DaoException the dao exception
     */
    public boolean updateUserName(String newLogin, String newEmail, String oldLogin) throws DaoException;

    /**
     * Find by full text by mysql.
     *
     * @param text the text for search
     * @return the deque with finding users
     * @throws DaoException the dao exception
     */
    public Deque<User> findByFullText(String text) throws DaoException;

    /**
     * Block user boolean.
     *
     * @param login the login of user
     * @return the boolean
     * @throws DaoException the dao exception
     */
    public boolean blockUser(String login) throws DaoException;

    /**
     * Unblock user.
     *
     * @param login the login of user
     * @return the boolean
     * @throws DaoException the dao exception
     */
    public boolean unblockUser(String login) throws DaoException;

    /**
     * Find status by login.
     *
     * @param login the login of user
     * @return the optional with status of user
     * @throws DaoException the dao exception
     */
    public Optional<Status> findStatusByLogin(String login) throws DaoException;

    /**
     * Find login by email.
     *
     * @param login the login of user
     * @return the optional with login of user
     * @throws DaoException the dao exception
     */
    public Optional<String> findLoginByEmail(String login) throws DaoException;

    /**
     * Sets password by id.
     *
     * @param id       the id of user
     * @param password the password
     * @param salt     the salt
     * @throws DaoException the dao exception
     */
    public void setPasswordById(long id, String password, String salt) throws DaoException;

    /**
     * Find info for rates.
     *
     * @param login the login
     * @return the map with information of rates
     * @throws DaoException the dao exception
     */
    public Map<String, Long> findInfoForRates(String login) throws DaoException;

    /**
     * Sets rates.
     *
     * @param login     the login of user
     * @param ratesType the rates type of user
     * @return the rates of current user
     * @throws DaoException the dao exception
     */
    public boolean setRates(String login, RatesType ratesType) throws DaoException;

    /**
     * Find count of users.
     *
     * @return the int, with count of users
     * @throws DaoException the dao exception
     */
    public int countOfUsers() throws DaoException;

    /**
     * Find all with limit, for pagination.
     *
     * @param offset the offset
     * @param limit  the limit
     * @return the deque with users in limit
     * @throws DaoException the dao exception
     */
    public Deque<User> findAll(int offset, int limit) throws DaoException;

    /**
     * Find role by login.
     *
     * @param login the login of user
     * @return the optional with role of user
     * @throws DaoException the dao exception
     */
    public Optional<Role> findRoleByLogin(String login) throws DaoException;
}

