package com.hongdingltd.utils;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by jcchen on 15-11-26.
 */
public class SysUtil {

    public static String getPrinciple() {
        String username = null;
        Object principle = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principle instanceof UserDetails) {
            username = ((UserDetails)principle).getUsername();
        } else {
            username = principle.toString();
        }
        return username;
    }

    public static boolean isAjax(HttpServletRequest request) {
        return "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));
    }
}
