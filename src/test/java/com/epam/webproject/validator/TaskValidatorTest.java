package com.epam.webproject.validator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TaskValidatorTest {

    @DataProvider(name = "validTitle")
    public static Object[][] validTitle() {
        return new Object[][]{{"1"}, {"Work 1"}, {"math"}};
    }

    @Test(dataProvider = "validTitle")
    public void checkEmailPositiveTest(String title) {
        Assert.assertTrue(TaskValidator.checkTitle(title));
    }

    @DataProvider(name = "invalidTitle")
    public static Object[][] invalidTitle() {
        return new Object[][]{{""}, {null}};
    }

    @Test(dataProvider = "invalidTitle")
    public void checkEmailNegativeTest(String title) {
        Assert.assertTrue(TaskValidator.checkTitle(title));
    }
}
