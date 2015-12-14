package com.hongdingltd.hello;

/**
 * Created by jcchen on 15-12-1.
 */
public class EnumTest {
    enum Gender {
        MALE(1), FEMALE(2);

        Integer value;

        Gender(Integer i) {
            this.value = i;
        }

        @Override
        public String toString() {
            return value.toString();
        }
    }

    /*
    public static void main(String[] args) {
        Object[] enumValues = Gender.class.getEnumConstants();
        System.out.println(enumValues);
        for (Object enumValue : enumValues) {
            System.out.println(enumValue.toString());
        }
    }
    */
}
