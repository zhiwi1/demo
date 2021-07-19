package com.epam.webproject.util;

import org.apache.commons.lang3.RandomStringUtils;
import org.mindrot.jbcrypt.BCrypt;

public class PasswordEncryptor {
    private static final PasswordEncryptor instance = new PasswordEncryptor();

    private PasswordEncryptor() {
    }

    public static PasswordEncryptor getInstance() {
        return instance;
    }

    public boolean checkHash(String password, String hash) {
        return BCrypt.checkpw(password, hash);
    }

    public String getHash(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(12));
    }

    public String generateSalt() {
        return BCrypt.gensalt(12);
    }

    public String getHash(String password, String salt) {
        return BCrypt.hashpw(password, salt);
    }
}