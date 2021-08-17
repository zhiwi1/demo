package com.epam.webproject.validator;

import org.testng.Assert;
import org.testng.annotations.Test;

public class UserValidatorTest {
    @Test
    public void validateComplexityTest() {
     String password=  "[A-zА-яЁё0-9_!@ -]{6,12}$";
  String EmailOrLoginRegexp="^(([A-Za-z0-9_.-]){2,205}@([A-Za-z0-9_-]){2,20}.([a-za-zЁё]){2,20})||(.{2,100})$";
     String testString="Qwe123";
        Assert.assertTrue( testString.matches(password));
    }
}
