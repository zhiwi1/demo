package com.epam.webproject.validator;

public class TaskValidator extends BaseValidator {
    private static final String REGEXP_TASK_PROP = "regexp.task";
    private static final String REGEXP_COMPLEXITY_PROP = "regexp.complexity";
    private static final String REGEXP_TITLE_PROP = "regexp.title";

    private TaskValidator() {
    }

    public static boolean checkComplexity(String complexity) {
        final String COMPLEXITY_REGEXP = regexpPropertyUtil.getProperty(REGEXP_COMPLEXITY_PROP);
        return isMatchFounded(complexity, COMPLEXITY_REGEXP);
    }

    public static boolean checkLength(String text) {
        final String TEXT_REGEXP = regexpPropertyUtil.getProperty(REGEXP_TASK_PROP);
        return isMatchFounded(text, TEXT_REGEXP);
    }

    public static boolean checkTitle(String title) {
        final String TITLE_REGEXP = regexpPropertyUtil.getProperty(REGEXP_TITLE_PROP);
        return isMatchFounded(title, TITLE_REGEXP);
    }

}
