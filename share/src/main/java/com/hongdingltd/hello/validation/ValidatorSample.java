package com.hongdingltd.hello.validation;

import org.joda.time.DateTime;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Date;
import java.util.Set;


/**
 * Created by jcchen on 15-12-1.
 */
public class ValidatorSample {

    /*
    public static void main(String[] args) {
        Order order = initOrder();
        doValidate(order);

        Signup signup = initSignup();
        doValidate(signup);

        OrderQuery orderQuery = initOrderQuery();
        doValidate(orderQuery);
    }
    */

    static Signup initSignup() {
        Signup signup = new Signup();
        signup.setUsername("tom");
        signup.setPassword("hello1");
        signup.setConfirmPassword("hello1");
        return signup;
    }

    static Order initOrder() {
        Order order = new Order();
        order.setOrderId("1234567890");
        order.setCustomer("sample customer");
        order.setCreateDate(new Date());
        order.setStatus("created");
        order.setAddress("深圳市福田区");

        Product product = new Product();
        product.setProductName("益力多");
        product.setPrice(6000f);

        order.setProduct(product);
        return order;
    }

    static OrderQuery initOrderQuery() {
        OrderQuery orderQuery = new OrderQuery();
        DateTime now = new DateTime();
        orderQuery.setFrom(now.toDate());
        orderQuery.setTo(now.plusDays(1).toDate());
        return orderQuery;
    }

    static <T> void doValidate(T object) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<T>> violations = validator.validate(object);

        if (violations.size() > 0)
            violations.forEach(e -> System.out.println(e.getPropertyPath()+": "+e.getMessage()));
        else
            System.out.println("验证通过");
    }

}
