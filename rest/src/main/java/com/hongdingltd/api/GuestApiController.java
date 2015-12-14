package com.hongdingltd.api;

import com.hongdingltd.form.User;
import com.hongdingltd.repository.UserRepository;
import com.hongdingltd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;

/**
 * Created by jcchen on 15-12-14.
 */
@RestController
@RequestMapping("/guest/api")
public class GuestApiController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/userexist", method = RequestMethod.GET)
    public boolean userExist(@RequestParam("username") String name) {
        return userService.findByUsername(name) != null;
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String signUp(@Valid User user, BindingResult result, Map<String, Object> model) {
        if (result.hasErrors()) {
            model.put("errors", result.getFieldErrors());
            return "signup";
        }
        // TODO: save user

        return null;
    }
}
