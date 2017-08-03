package com.teamcore.site.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private DataSource dataSource;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        final String usersByUsernameQuery = "select c_name, c_password, c_enabled from t_users " +
                "where c_name = ?";
        final String authoritiesByUsernameQuery = "select t_users.c_name, t_user_groups.c_name " +
                "from t_users join t_user_groups on t_users.c_user_group_id = t_user_groups.c_group_id " +
                "where t_users.c_name = ?";

        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery(usersByUsernameQuery)
                .authoritiesByUsernameQuery(authoritiesByUsernameQuery);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/").authenticated().anyRequest().permitAll()
        .and()
        .formLogin()
        .loginProcessingUrl("/login").loginPage("/login")
        .failureHandler((httpServletRequest, httpServletResponse, e) -> {
            httpServletResponse.addHeader("authFail", "true");
            httpServletResponse.sendRedirect("/login");
        })
        .permitAll();
    }
}
