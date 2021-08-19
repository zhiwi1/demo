package com.epam.webproject.validator;

import com.epam.webproject.util.RegexpPropertyUtil;

abstract class BaseValidator {
    static final RegexpPropertyUtil regexpPropertyUtil = RegexpPropertyUtil.getInstance();

    static boolean isMatchFounded(String text, String regexp) {
        return text != null && text.matches(regexp);
    }
}
