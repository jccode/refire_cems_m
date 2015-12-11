package com.hongdingltd.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.hongdingltd.annotation.Enum;

/**
 * Created by jcchen on 15-12-1.
 */
public class EnumValidation implements ConstraintValidator<Enum, Object> {

    private Object[] enumValues;
//    private boolean ignoreCase;

    @Override
    public void initialize(Enum annotation) {
        enumValues = annotation.enumClass().getEnumConstants();
//        ignoreCase = annotation.ignoreCase();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null || enumValues == null) return true;
        boolean result = false;
        for (Object enumValue : enumValues) {
            if (value.toString().equals(enumValue.toString())) {
                result = true;
                break;
            }
        }
        return result;
    }
}
