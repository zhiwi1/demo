package com.epam.webproject.validator;

import org.testng.Assert;
import org.testng.annotations.Test;

public class UserValidatorTest {
    @Test
    public void validateComplexityTest() {
  String EmailOrLoginRegexp="^(([A-Za-z0-9_.-]){2,205}@([A-Za-z0-9_-]){2,20}.([a-za-zЁё]){2,20})||(.{2,100})$";
     String testString="zz";
        Assert.assertTrue( testString.matches(EmailOrLoginRegexp));
    }
}
