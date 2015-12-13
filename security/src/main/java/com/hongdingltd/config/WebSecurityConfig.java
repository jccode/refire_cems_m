package com.hongdingltd.config;


import com.hongdingltd.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

import javax.sql.DataSource;

/**
 * Created by jcchen on 15-12-10.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Autowired
    private DataSource dataSource;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
//                .withDefaultSchema()
                .passwordEncoder(new BCryptPasswordEncoder());
    }


    @Configuration
    @Order(1)
    public static class ApiWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.antMatcher("/api/**")
                    .authorizeRequests().anyRequest().authenticated()
                    .and().httpBasic()
                    .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER)
                    .and().csrf().disable()
                    .addFilterBefore(new CorsFilter(), ChannelProcessingFilter.class);
        }
    }

    @Configuration
    public static class FormLoginWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {

        @Autowired
        private LoginSuccessHandler loginSuccessHandler;

        @Autowired
        private DataSource dataSource;

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests()
                    .antMatchers("/webjars/**").permitAll()
                    .antMatchers("/js/**", "/css/**", "/img/**").permitAll()
                    .antMatchers("/login*", "/signup", "/signup/**").permitAll()
                    .antMatchers("/guest/**").permitAll()
                    //.antMatchers("/", "/user/*", "/greeting").permitAll()
                    .antMatchers("/admin/**").hasRole("ADMIN")
                    .antMatchers("/dba/**").access("hasRole('ADMIN') and hasRole('DBA')")
                    .anyRequest().authenticated()
                    .and().formLogin().loginPage("/login").permitAll().successHandler(loginSuccessHandler)
                    .failureHandler(new LoginFailHandler("/login?error"))
                    .and().csrf().csrfTokenRepository(csrfTokenRepository()) //.ignoringAntMatchers("/login", "/logout", "/api/**")
                    .and().rememberMe().tokenRepository(persistentTokenRepository()).tokenValiditySeconds(648000) // half year.
                    .and().logout().invalidateHttpSession(true).deleteCookies("remember-me")
                    .logoutSuccessHandler(new LogoutSuccessHandler("/login?logout")).permitAll()
                    .and().exceptionHandling().accessDeniedPage("/access_denied")
                    .and().addFilterAfter(new AngularCsrfHeaderFilter(), CsrfFilter.class)
                    ;
        }

        private CsrfTokenRepository csrfTokenRepository() {
            HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
            repository.setHeaderName("X-XSRF-TOKEN");
            return repository;
        }

        private PersistentTokenRepository persistentTokenRepository() {
            JdbcTokenRepositoryImpl db = new JdbcTokenRepositoryImpl();
            db.setDataSource(dataSource);
            return db;
        }
    }


}
