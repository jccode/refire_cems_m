package com.hongdingltd.hello.validation.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Created by jcchen on 15-12-1.
 */

@Constraint(validatedBy = StatusValidator.class)
@Documented
@Target({ElementType.ANNOTATION_TYPE, ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Status {

    String message() default "{com.hongdingltd.hello.validation.annotation.Status.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
