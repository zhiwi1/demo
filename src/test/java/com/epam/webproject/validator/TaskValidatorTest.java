package com.epam.webproject.validator;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TaskValidatorTest {

    @DataProvider(name = "validTitle")
    public static Object[][] validTitle() {
        return new Object[][]{{"1"}, {"Work 1"}, {"math"}};
    }

    @Test(dataProvider = "validTitle")
    public void checkTitlePositiveTest(String title) {
        Assert.assertTrue(TaskValidator.checkTitle(title));
    }

    @DataProvider(name = "invalidTitle")
    public static Object[][] invalidTitle() {
        return new Object[][]{{""}, {null}};
    }

    @Test(dataProvider = "invalidTitle")
    public void checkTitleNegativeTest(String title) {
        Assert.assertFalse(TaskValidator.checkTitle(title));
    }

    @DataProvider(name = "validComplexity")
    public static Object[][] validComplexity() {
        return new Object[][]{{"1"}, {"2"}, {"3"}, {"4"}, {"5"}, {"6"}, {"7"}, {"8"}, {"9"}, {"10"}};
    }

    @Test(dataProvider = "validComplexity")
    public void checkComplexityPositiveTest(String complexity) {
        Assert.assertTrue(TaskValidator.checkComplexity(complexity));
    }

    @DataProvider(name = "invalidComplexity")
    public static Object[][] invalidComplexity() {
        return new Object[][]{{"0"}, {null}, {"1231323"}, {" 4 "}, {"5123"}, {"61213"}, {"712"}, {"812"}, {"9///"}, {"<10>"}};
    }

    @Test(dataProvider = "invalidComplexity")
    public void checkComplexityNegativeTest(String complexity) {
        Assert.assertFalse(TaskValidator.checkComplexity(complexity));
    }


    @DataProvider(name = "validLength")
    public static Object[][] validLength() {
        return new Object[][]{{"1234"}, {"           "}};
    }

    @Test(dataProvider = "validLength")
    public void checkLengthPositiveTest(String test) {
        Assert.assertTrue(TaskValidator.checkLength(test));
    }

    @DataProvider(name = "invalidLength")
    public static Object[][] invalidLength() {
        return new Object[][]{{"1"}, {"w2"}, {"333"}, {""}, {null}};
    }

    @Test(dataProvider = "invalidLength")
    public void checkLengthNegativeTest(String test) {
        Assert.assertFalse(TaskValidator.checkLength(test));
    }

}
