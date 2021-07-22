package com.epam.webproject.validator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class PostValidatorTest {
    private final static Logger logger = LogManager.getLogger();


    @Test
    public void validateComplexityTest() {
        String x = "1";
        boolean actualResult = PostValidator.checkComplexity(x);
        boolean expected = true;
        Assert.assertTrue(actualResult);
    }
}
