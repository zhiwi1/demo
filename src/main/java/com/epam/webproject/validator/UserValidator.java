package com.epam.webproject.validator;


    public class UserValidator {
        private static final String EMAIL_PATTERN = "^([A-Za-z0-9_.-])+@([A-Za-z0-9_-]){2,}.([a-za-zЁё]){2,}$";
        private static final String PASSWORD_PATTERN = "^([A-zА-яЁё0-9_!@ -]){6,}$";

        private UserValidator() {}

        public static boolean checkEmail(String email) {
            return email.matches(EMAIL_PATTERN);
        }

        public static boolean checkPassword(String password) {
            return password.matches(PASSWORD_PATTERN);
        }
    }

