package com.hongdingltd.web;

import com.hongdingltd.form.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Map;


/**
 * Created by jcchen on 15-11-25.
 */
@Controller
public class PageController {

    @RequestMapping(value = {"/", "/home"})
    public String homePage(Map<String, Object> model) {
        model.put("title", "Welcome to mysite");
        return "index";
    }

    @RequestMapping("/admin")
    public String adminPage(Map<String, Object> model) {
        model.put("user", getPrinciple());
        return "admin";
    }

    @RequestMapping("/dba")
    public String dbaPage(Map<String, Object> model) {
        model.put("user", getPrinciple());
        return "dba";
    }

    @RequestMapping("/access_denied")
    public String accessDeniedPage(Map<String, Object> model, HttpServletRequest request) {
        model.put("user", getPrinciple());
//        CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
//        model.put("_csrf", token);
        return "access_denied";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(@RequestParam(value = "error", required = false)String error,
                            @RequestParam(value = "logout", required = false)String logout,
                            Map<String, Object> model, HttpServletRequest request) {
//        CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
//        model.put("_csrf", token);
        if (error != null) {
            model.put("error", "Invalid username and password!");
        }
        if (logout != null) {
            model.put("msg", "You've been logged out successfully.");
        }
        return "login";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String signUpPage() {
        return "signup";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String signUp(@Valid User user, BindingResult result, Map<String, Object> model) {
        if (result.hasErrors()) {
            model.put("errors", result.getFieldErrors());
            return "signup";
        }
        // save user
        return "redirect:/signup/success";
    }

    @RequestMapping("/guest/ajaxlogin")
    public String ajaxLogin() {
        return "ajaxlogin";
    }

    @RequestMapping("/guest/angular_login")
    public String angularLogin() {
        return "angular_login";
    }

    @RequestMapping(path = "/upload", method = RequestMethod.GET)
    public String fileUpload() {
        return "upload";
    }

    @RequestMapping(path = "/guest/annotest", method = RequestMethod.GET)
    public String annoTest() {
        return "anno_test";
    }

    private String getPrinciple() {
        String username = null;
        Object principle = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principle instanceof UserDetails) {
            username = ((UserDetails)principle).getUsername();
        } else {
            username = principle.toString();
        }
        return username;
    }
}
