package com.hongdingltd.api;


import com.hongdingltd.domain.User;
import com.hongdingltd.hello.HelloService;
import com.hongdingltd.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.security.Principal;

/**
 * Created by jcchen on 15-12-6.
 */
@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private UserRepository repository;

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public User currentUser(Principal user) {
        System.out.println(user);

        return repository.findByUsername(user.getName());
    }

    @RequestMapping(path = "/upload", method = RequestMethod.POST)
    public @ResponseBody
    String handleFormUpload(@RequestParam("name") String name, @RequestParam("file")MultipartFile file) {
        if (!file.isEmpty()) {
            try {

                String dir = "/home/jcchen/temp/upload/";

//                byte[] bytes = file.getBytes();
//                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(dir+name)));
//                stream.write(bytes);
//                stream.close();

                File f = new File(dir+name);
                System.out.println(f.getAbsolutePath());
                file.transferTo(f);
                return "You successfully uploaded "+name+"!";
            } catch (IOException e) {
                e.printStackTrace();
                return "You failed to upload " + name + " => "+e.getMessage();
            }
        } else {
            return "You failed to upload " + name + " because file was empty.";
        }
    }


}
