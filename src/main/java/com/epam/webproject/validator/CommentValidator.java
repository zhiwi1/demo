package com.epam.webproject.validator;

public class CommentValidator extends BaseValidator {
    private static final String REGEXP_COMMENT_PROP = "regexp.comment";

    private CommentValidator() {
    }

    public static boolean checkComment(String comment) {
        final String COMMENT_REGEXP = regexpPropertyUtil.getProperty(REGEXP_COMMENT_PROP);
        return isMatchFounded(comment, COMMENT_REGEXP);
    }

}
