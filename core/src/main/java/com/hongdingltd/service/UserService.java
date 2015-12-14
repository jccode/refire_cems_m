package com.hongdingltd.service;

import com.google.common.collect.Sets;
import com.hongdingltd.domain.Authority;
import com.hongdingltd.domain.User;
import com.hongdingltd.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Created by jcchen on 15-12-14.
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User signup(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
//        TODO:
//        Authority authority =
//        Set<Authority> authorities = Sets.newHashSet();
//        user.setAuthorities();
        return null;
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
