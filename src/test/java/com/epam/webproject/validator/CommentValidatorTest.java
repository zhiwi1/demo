package com.epam.webproject.validator;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CommentValidatorTest {
    @DataProvider(name = "validComment")
    public static Object[][] validComment() {
        return new Object[][]{{"1"}, {"Work 1"}, {"math"}};
    }

    @Test(dataProvider = "validComment")
    public void checkCommentPositiveTest(String comment) {
        Assert.assertTrue(CommentValidator.checkComment(comment));
    }

    @DataProvider(name = "invalidComment")
    public static Object[][] invalidComment() {
        return new Object[][]{{""}, {null}};
    }

    @Test(dataProvider = "invalidComment")
    public void checkCommentNegativeTest(String comment) {
        Assert.assertFalse(CommentValidator.checkComment(comment));
    }

}
