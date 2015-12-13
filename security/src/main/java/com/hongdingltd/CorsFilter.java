package com.hongdingltd;

import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

/**
 * Created by jcchen on 15-12-7.
 */
public class CorsFilter extends OncePerRequestFilter {
    static final String ORIGIN = "Origin";
    static final String[] WHITELIST = new String[]{"localhost", "127.0.0.1"};

    private boolean originAcceptable(String origin) {
        if (origin == null || origin.equals("null")) {
            return true;
        } else {
            return Arrays.stream(WHITELIST).anyMatch(s -> origin.contains(s));
        }
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String origin = request.getHeader(ORIGIN);
//        System.out.println("[CorsFilter] : " + origin);
        if (originAcceptable(origin)) {
            response.addHeader("Access-Control-Allow-Origin", origin);
            response.addHeader("Access-Control-Allow-Credentials", "true");
            response.addHeader("Access-Control-Max-Age", "10");

            String headers = request.getHeader("Access-Control-Request-Headers");
            if (!StringUtils.isEmpty(headers)) {
                response.addHeader("Access-Control-Allow-Headers", headers);
            }
        }
        if (request.getMethod().equals("OPTIONS")) {
            try {
                response.getWriter().print("OK");
                response.getWriter().flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            filterChain.doFilter(request, response);
        }
    }
}
