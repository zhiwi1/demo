package com.epam.webproject.validator;

import com.epam.webproject.util.RegexpPropertyUtil;

public class TaskValidator {
    private static final String REGEXP_TASK_PROP = "regexp.task";
    private static final String REGEXP_COMPLEXITY_PROP = "regexp.complexity";
    private static final String REGEXP_TITLE_PROP ="regexp.title";
    private static final RegexpPropertyUtil regexpPropertyUtil = RegexpPropertyUtil.getInstance();

    private TaskValidator() {
    }

    public static boolean checkComplexity(String complexity) {
        final String COMPLEXITY_REGEXP = regexpPropertyUtil.getProperty(REGEXP_COMPLEXITY_PROP);
        return complexity.matches(COMPLEXITY_REGEXP);
    }

    public static boolean checkLength(String text) {
        final String TEXT_REGEXP = regexpPropertyUtil.getProperty(REGEXP_TASK_PROP);
        return text.matches(TEXT_REGEXP);
    }
    public static boolean checkTitle(String title){
        final String TITLE_REGEXP = regexpPropertyUtil.getProperty(REGEXP_TITLE_PROP);
        return title.matches(TITLE_REGEXP);
    }

}
