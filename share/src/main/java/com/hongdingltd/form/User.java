package com.hongdingltd.form;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import com.hongdingltd.annotation.Enum;

/**
 * Created by jcchen on 15-11-30.
 */
public class User {

    public static enum Gender {
        MALE(1), FEMALE(2);

        private Integer value;

        Gender(Integer i) {
            this.value = i;
        }

        @Override
        public String toString() {
            return this.value.toString();
        }
    }

    @Size(min = 6, max = 30)
    private String username;

    @Pattern(regexp = "[a-zA-Z0-9]{6,20}")
    private String password;

    @Min(0)
    @Max(100)
    private Integer age;

    @Enum(enumClass = Gender.class)
    private Integer gender;

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }
}
