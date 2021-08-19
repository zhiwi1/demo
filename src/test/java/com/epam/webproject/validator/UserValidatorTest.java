package com.epam.webproject.validator;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class UserValidatorTest {

    @DataProvider(name = "validEmail")
    public static Object[][] validEmail() {
        return new Object[][]{{"zhiweiwq@gmail.com"}, {"asdsadsad@tut.by"}, {"dzhon199168@gmail.ru"}};
    }

    @Test(dataProvider = "validEmail")
    public void checkEmailPositiveTest(String email) {
        Assert.assertTrue(UserValidator.checkEmail(email));
    }

    @DataProvider(name = "invalidEmail")
    public static Object[][] invalidEmail() {
        return new Object[][]{{"zhyuliuk"}, {null}, {"zhyuliuk@gmailcom"}, {""}, {"         "}, {"nnnnnnnnn"}};
    }

    @Test(dataProvider = "invalidEmail")
    public void validateEmailNegativeTest(String email) {
        Assert.assertFalse(UserValidator.checkEmail(email));
    }

    @DataProvider(name = "validLogin")
    public static Object[][] validLogin() {
        return new Object[][]{{"zqweqew123"}, {"asdsads432"}, {"dzhon199168"}};
    }

    @Test(dataProvider = "validLogin")
    public void checkLoginPositiveTest(String login) {
        Assert.assertTrue(UserValidator.checkLogin(login));
    }

    @DataProvider(name = "invalidLogin")
    public static Object[][] invalidLogin() {
        return new Object[][]{{""}, {null}, {" "}, {"1"}};
    }

    @Test(dataProvider = "invalidLogin")
    public void checkLoginNegativeTest(String login) {
        Assert.assertFalse(UserValidator.checkLogin(login));
    }

    @DataProvider(name = "validPassword")
    public static Object[][] validPassword() {
        return new Object[][]{{"Qwe123"}, {"VanOd432"}, {"dzhOn199168"}, {"123456"}, {"goodjob"}};
    }

    @Test(dataProvider = "validPassword")
    public void checkPasswordPositiveTest(String password) {
        Assert.assertTrue(UserValidator.checkPassword(password));
    }

    @DataProvider(name = "invalidPassword")
    public static Object[][] invalidPassword() {
        return new Object[][]{{"12312"}, {"d432"}, {null}, {" "}, {"321"}};
    }

    @Test(dataProvider = "invalidPassword")
    public void checkPasswordNegativeTest(String password) {
        Assert.assertFalse(UserValidator.checkPassword(password));
    }

}
