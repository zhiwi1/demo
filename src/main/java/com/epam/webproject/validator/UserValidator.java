package com.epam.webproject.validator;


    public class UserValidator {
        //todo validation length
        private static final String EMAIL_REGEXP = "^([A-Za-z0-9_.-])+@([A-Za-z0-9_-]){2,235}.([a-za-zЁё]){2,20}$";
        private static final String PASSWORD_REGEXP = "^([A-zА-яЁё0-9_!@ -]){6,12}$";
        private static final String TEXT_REGEXP = ".{2,100}";

        private UserValidator() {}

        public static boolean checkEmail(String email) {
            return email.matches(EMAIL_REGEXP);
        }

        public static boolean checkPassword(String password) {
            return password.matches(PASSWORD_REGEXP);
        }

        public static boolean checkPasswordAndConfirmPassword(String password,String confirmPassword){return password.equals(confirmPassword);}

        public static boolean checkLength(String text) {
            return text.matches(TEXT_REGEXP);
        }
    }

