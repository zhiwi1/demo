package com.epam.webproject.validator;

import com.epam.webproject.util.RegexpPropertyUtil;

public class CommentValidator {
    private static final String REGEXP_COMMENT_PROP = "regexp.comment";
    private static final RegexpPropertyUtil regexpPropertyUtil = RegexpPropertyUtil.getInstance();

    private CommentValidator() {
    }

    public static boolean checkComment(String comment) {
        final String COMMENT_REGEXP = regexpPropertyUtil.getProperty(REGEXP_COMMENT_PROP);
        return comment.matches(COMMENT_REGEXP);
    }

}
