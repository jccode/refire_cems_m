package com.hongdingltd.hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * Created by jcchen on 15-12-12.
 */
@Service
public class HelloService {

    @Autowired
    private StringRedisTemplate chatTemplate;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public void chat(String msg) {
        chatTemplate.convertAndSend("chat", msg);
    }

    public void helloObject(HashMap<String, String> map) {
        redisTemplate.convertAndSend("generic", map);
        System.out.println("send: " + map);
    }
}
