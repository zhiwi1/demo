package com.epam.webproject.validator;

public class CommentValidator {
    private static final String COMMENT_REGEXP = "^.+$";


    private CommentValidator() {}

    public static boolean checkComment(String comment) {
        return comment.matches(COMMENT_REGEXP);
    }

}
