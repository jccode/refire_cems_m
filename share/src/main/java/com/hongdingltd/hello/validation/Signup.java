package com.hongdingltd.hello.validation;

import com.hongdingltd.hello.validation.annotation.Matches;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by jcchen on 15-12-1.
 */
@Matches(field = "password", verifyField = "confirmPassword")
public class Signup {

    @NotNull
    private String username;

    @NotNull
    @Size(min = 6, max = 20)
    private String password;

    @NotNull
    @Size(min = 6, max = 20)
    private String confirmPassword;

    public Signup() {
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

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
