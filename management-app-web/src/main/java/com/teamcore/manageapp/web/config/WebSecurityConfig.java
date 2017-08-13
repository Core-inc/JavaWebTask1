package com.teamcore.manageapp.web.config;

import com.teamcore.manageapp.service.config.ServiceSecurityConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.servlet.http.HttpSession;

@Configuration
@EnableWebSecurity
@Order(1)
public class WebSecurityConfig extends ServiceSecurityConfig {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests().antMatchers("/").authenticated().anyRequest().permitAll()
//        .and()
//        .formLogin()
//        .loginProcessingUrl("/login").loginPage("/login")
//        .failureHandler((httpServletRequest, httpServletResponse, e) -> {
//            HttpSession session = httpServletRequest.getSession();
//            session.setAttribute("authFail", "true");
//            httpServletResponse.sendRedirect("/login");
//        })
//        .permitAll();

        http.authorizeRequests().antMatchers("/**").permitAll();

        http.csrf().disable();
    }
}
