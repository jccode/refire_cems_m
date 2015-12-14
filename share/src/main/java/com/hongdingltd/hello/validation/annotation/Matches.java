package com.hongdingltd.hello.validation.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Created by jcchen on 15-12-1.
 */
@Constraint(validatedBy = MatchesValidator.class)
@Documented
@Target({ElementType.ANNOTATION_TYPE, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Matches {

    String message() default "{com.hongdingltd.hello.validation.annotation.Matches.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String field();

    String verifyField();
}
