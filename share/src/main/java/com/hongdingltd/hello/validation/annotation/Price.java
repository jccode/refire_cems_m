package com.hongdingltd.hello.validation.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.lang.annotation.*;

/**
 * Created by jcchen on 15-12-1.
 */
@Max(10000)
@Min(5000)
@Constraint(validatedBy = {})
@Documented
@Target({ElementType.ANNOTATION_TYPE, ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Price {

    String message() default "{com.hongdingltd.hello.validation.annotation.Price.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
