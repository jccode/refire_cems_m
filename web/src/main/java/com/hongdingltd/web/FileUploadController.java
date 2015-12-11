package com.hongdingltd.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * Created by jcchen on 15-12-8.
 */
@Controller
public class FileUploadController {

    @RequestMapping(path = "/upload", method = RequestMethod.POST)
    public @ResponseBody String handleFormUpload(@RequestParam("name") String name,
                                   @RequestParam("file")MultipartFile file) {
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
