package com.epam.webproject.validator;

public class AnswerValidator extends BaseValidator {
    private static final String REGEXP_COMMENT_PROP = "regexp.answer";

    private AnswerValidator() {
    }

    public static boolean checkAnswer(String answer) {
        final String ANSWER_REGEXP = regexpPropertyUtil.getProperty(REGEXP_COMMENT_PROP);
        return isMatchFounded(answer, ANSWER_REGEXP);
    }

}
