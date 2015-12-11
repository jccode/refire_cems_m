package com.hongdingltd;

import com.hongdingltd.utils.SysUtil;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by jcchen on 15-11-28.
 */
public class LoginFailHandler extends SimpleUrlAuthenticationFailureHandler {

    private String defaultFailureUrl;

    public LoginFailHandler(String defaultFailureUrl) {
        super(defaultFailureUrl);
        this.defaultFailureUrl = defaultFailureUrl;
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        if (SysUtil.isAjax(request)) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED,
                    "Authentication Failed: " + exception.getMessage());
            logger.debug("ajax login failed", exception);

//            response.setContentType("application/json;charset=UTF-8");
//            response.setHeader("Cache-Control", "no-cache");
//            response.getWriter().write("false");
        } else {
            super.onAuthenticationFailure(request, response, exception);
        }
    }
}
