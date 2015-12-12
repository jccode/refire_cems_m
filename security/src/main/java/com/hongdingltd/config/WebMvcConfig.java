package com.hongdingltd.config;


import com.hongdingltd.MustacheCsrfInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by jcchen on 15-11-27.
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private MustacheCsrfInterceptor csrfInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        super.addInterceptors(registry);
        registry.addInterceptor(csrfInterceptor);
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/signup/success").setViewName("signup_success");
    }

    @Bean
    public HttpMessageConverters customConverters() {
        HttpMessageConverter<?> jsonConverter = new MappingJackson2HttpMessageConverter();
        return new HttpMessageConverters(jsonConverter);
    }
}
