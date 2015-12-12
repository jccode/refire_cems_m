package com.hongdingltd.api;

import com.google.common.collect.Maps;
import com.hongdingltd.hello.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jcchen on 15-12-12.
 */
@RestController
@RequestMapping("/api/hello")
public class HelloController {

    @Autowired
    private HelloService helloService;

    @RequestMapping(path = "/chat", method = RequestMethod.POST)
    public void helloChat(@RequestBody String message) {
        helloService.chat(message);
    }

    @RequestMapping(path = "/user", method = RequestMethod.POST)
    public void helloObject(@RequestBody HashMap<String, String> user) {
        System.out.println(user);
//        System.out.println(name);
//        Map<String, String> obj = Maps.newHashMap();
//        obj.put("name", "tom");
        helloService.helloObject(user);
    }
}
