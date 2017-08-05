package com.teamcore.manageapp.web.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.servlet.http.HttpSession;

public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/").authenticated().anyRequest().permitAll()
        .and()
        .formLogin()
        .loginProcessingUrl("/login").loginPage("/login")
        .failureHandler((httpServletRequest, httpServletResponse, e) -> {
            HttpSession session = httpServletRequest.getSession();
            session.setAttribute("authFail", "true");
            httpServletResponse.sendRedirect("/login");
        })
        .permitAll();
    }
}
