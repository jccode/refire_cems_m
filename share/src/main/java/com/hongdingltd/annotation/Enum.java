package com.hongdingltd.annotation;

import com.hongdingltd.validation.EnumValidation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Created by jcchen on 15-12-1.
 */
@Constraint(validatedBy = EnumValidation.class)
@Documented
@Target({ElementType.ANNOTATION_TYPE, ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Enum {

    String message() default "{com.hongdingltd.validation.annotation.Enum.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    Class<? extends java.lang.Enum<?>> enumClass();

//    boolean ignoreCase() default false;
}
