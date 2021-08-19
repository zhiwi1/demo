package com.epam.webproject.validator;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class AnswerValidatorTest {
    @DataProvider(name = "validAnswer")
    public static Object[][] validAnswer() {
        return new Object[][]{{"12"}, {"Work 1"}, {"math"}};
    }

    @Test(dataProvider = "validAnswer")
    public void checkAnswerPositiveTest(String answer) {
        Assert.assertTrue(AnswerValidator.checkAnswer(answer));
    }

    @DataProvider(name = "invalidAnswer")
    public static Object[][] invalidAnswer() {
        return new Object[][]{{""}, {"1"}, {null}};
    }

    @Test(dataProvider = "invalidAnswer")
    public void checkAnswerNegativeTest(String answer) {
        Assert.assertFalse(AnswerValidator.checkAnswer(answer));
    }

}
