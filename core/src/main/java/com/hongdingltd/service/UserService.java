package com.hongdingltd.service;

import com.google.common.collect.Sets;
import com.hongdingltd.domain.Authority;
import com.hongdingltd.domain.User;
import com.hongdingltd.repository.AuthorityRepository;
import com.hongdingltd.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;

/**
 * Created by jcchen on 15-12-14.
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Transactional
    public User signup(String telphone, String password) {
        User user = new User();
        user.setTelphone(telphone);
        user.setPassword(password);
        userRepository.save(user);

//        TODO:
//        Authority authority = new Authority();
//        authority.setAuthority("");
//        Set<Authority> authorities = Sets.newHashSet();
//        user.setAuthorities();
        return null;
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
