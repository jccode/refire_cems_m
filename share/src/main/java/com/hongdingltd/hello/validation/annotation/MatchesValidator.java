package com.hongdingltd.hello.validation.annotation;


import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by jcchen on 15-12-1.
 */
public class MatchesValidator implements ConstraintValidator<Matches, Object> {

    private String field;
    private String verifyField;

    @Override
    public void initialize(Matches matches) {
        this.field = matches.field();
        this.verifyField = matches.verifyField();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        BeanWrapper wrapper = PropertyAccessorFactory.forBeanPropertyAccess(value);
        Object fieldValue = wrapper.getPropertyValue(field);
        Object verifyFieldValue = wrapper.getPropertyValue(verifyField);
        if(fieldValue == null && verifyFieldValue == null) {
            return true;
        }

        boolean match = (fieldValue != null) && fieldValue.equals(verifyFieldValue);
        if (!match) {
            String messageTemplate = context.getDefaultConstraintMessageTemplate();
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(messageTemplate)
                    .addPropertyNode(verifyField)
                    .addConstraintViolation();
        }

        return match;
    }
}
