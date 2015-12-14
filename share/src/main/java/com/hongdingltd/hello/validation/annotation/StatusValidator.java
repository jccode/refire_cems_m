package com.hongdingltd.hello.validation.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

/**
 * Created by jcchen on 15-12-1.
 */
public class StatusValidator implements ConstraintValidator<Status, String> {

    private final String[] ALL_STATUS = {"created", "paid", "shipped", "closed"};

    @Override
    public void initialize(Status constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return Arrays.asList(ALL_STATUS).contains(value);
    }
}
