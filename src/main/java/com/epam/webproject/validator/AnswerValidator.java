package com.epam.webproject.validator;

import com.epam.webproject.util.RegexpPropertyUtil;

public class AnswerValidator {
    private static final String REGEXP_COMMENT_PROP = "regexp.answer";
    private static final RegexpPropertyUtil regexpPropertyUtil = RegexpPropertyUtil.getInstance();

    private AnswerValidator() {
    }

    public static boolean checkAnswer(String answer) {
        final String ANSWER_REGEXP = regexpPropertyUtil.getProperty(REGEXP_COMMENT_PROP);
        return answer.matches(ANSWER_REGEXP);
    }

}
