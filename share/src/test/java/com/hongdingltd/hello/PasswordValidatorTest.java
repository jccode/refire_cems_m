package com.hongdingltd.hello;

import org.junit.BeforeClass;
import org.junit.Test;


import static org.junit.Assert.*;

/**
 * Created by jcchen on 15-12-1.
 */
public class PasswordValidatorTest {

    private static PasswordValidator passwordValidator;

    @BeforeClass
    public static void initData() {
        passwordValidator = new PasswordValidator();
    }

    @Test
    public void validPasswordTest() {
        String[] password = new String[] {
            "mkyong1A@", "mkYOn12$"
        };
        for (String temp : password) {
            boolean valid = passwordValidator.validate(temp);
            System.out.println("Password is valid: " + temp + " , " + valid);
            assertTrue(valid);
        }
    }

    @Test
    public void invalidPasswordTest() {
        String[] password = new String[] {
                "mY1A@","mkyong12@","mkyoNg12*",
                "mkyonG$$","MKYONG12$"
        };
        for (String temp : password) {
            boolean valid = passwordValidator.validate(temp);
            System.out.println("Password is valid: " + temp + " , " + valid);
            assertFalse(valid);
        }
    }
}
