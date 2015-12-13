package com.hongdingltd.api;

import com.hongdingltd.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jcchen on 15-12-14.
 */
@RestController
@RequestMapping("/guest/api")
public class GuestApiController {

    @Autowired
    private UserRepository repository;

    @RequestMapping(value = "/userexist", method = RequestMethod.GET)
    public boolean userExist(@RequestParam("username") String name) {
        return repository.findByUsername(name) != null;
    }
}
