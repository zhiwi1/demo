package com.epam.webproject.model.service;

import com.epam.webproject.exception.ServiceException;
import com.epam.webproject.model.entity.*;

import java.util.Deque;
import java.util.Optional;


/**
 * The interface User service.
 */
public interface UserService {

    /**
     * Register user feedback.
     *
     * @param login           the login
     * @param email           the email
     * @param password        the password
     * @param confirmPassword the second password for confirmation
     * @return the feedback
     * @throws ServiceException the service exception
     */
    public Feedback registerUser(String login, String email, String password, String confirmPassword) throws ServiceException;

    /**
     * Sign in user boolean.
     *
     * @param loginOrEmail the login or email
     * @param password     the password
     * @return the boolean (result of authentication)
     * @throws ServiceException the service exception
     */
    public boolean signInUser(String loginOrEmail, String password) throws ServiceException;

    /**
     * Find by login or email optional.
     *
     * @param login the login
     * @return the optional with user
     * @throws ServiceException the service exception
     */
    public Optional<User> findByLoginOrEmail(String login) throws ServiceException;


    /**
     * Update user boolean.
     *
     * @param newLogin the new login
     * @param newEmail the new email
     * @param oldLogin the old login
     * @param oldEmail the old email
     * @return the boolean
     * @throws ServiceException the service exception
     */
    public boolean updateUser(String newLogin, String newEmail, String oldLogin, String oldEmail) throws ServiceException;

    /**
     * Find by full text deque.
     *
     * @param text the text
     * @return the deque
     * @throws ServiceException the service exception
     */
    public Deque<User> findByFullText(String text) throws ServiceException;

    /**
     * Unblock user .
     *
     * @param login the login
     * @return the boolean (result of unblocking)
     * @throws ServiceException the service exception
     */
    public boolean unblockUser(String login) throws ServiceException;

    /**
     * Block user.
     *
     * @param login the login
     * @return the boolean (result of blocking)
     * @throws ServiceException the service exception
     */
    public boolean blockUser(String login) throws ServiceException;

    /**
     * Check user status.
     *
     * @param login          the login
     * @param expectedStatus the expected status
     * @return the boolean (true, if equals )
     * @throws ServiceException the service exception
     */
    public boolean checkUserStatus(String login, Status expectedStatus) throws ServiceException;

    /**
     * Find login optional.
     *
     * @param loginOrEmail the login or email
     * @return the optional with login
     * @throws ServiceException the service exception
     */
    public Optional<String> findLogin(String loginOrEmail) throws ServiceException;

    /**
     * Forget password boolean. Set new random password
     *
     * @param email the email
     * @return the boolean
     * @throws ServiceException the service exception
     */
    public boolean forgetPassword(String email) throws ServiceException;

    /**
     * Calculate rates of solve .
     *
     * @param login the login of user
     * @return the rates type
     * @throws ServiceException the service exception
     */
    public RatesType calculateRatesOfSolve(String login) throws ServiceException;

    /**
     * Sets rates.
     *
     * @param login     the login of user
     * @param ratesType the rates type
     * @return the rates
     * @throws ServiceException the service exception
     */
    public boolean setRates(String login, RatesType ratesType) throws ServiceException;

    /**
     * Count of users.
     *
     * @return the int(count)
     * @throws ServiceException the service exception
     */
    public int countOfUsers() throws ServiceException;

    /**
     * Find all users with limit for pagination.
     *
     * @param offset the offset
     * @param limit  the limit
     * @return the deque with users
     * @throws ServiceException the service exception
     */
    public Deque<User> findAllUsersWithLimit(int offset, int limit) throws ServiceException;

    /**
     * Find role by login .
     *
     * @param login the login
     * @return the optional with role of user
     * @throws ServiceException the service exception
     */
    public Optional<Role> findRoleByLogin(String login) throws ServiceException;

}
