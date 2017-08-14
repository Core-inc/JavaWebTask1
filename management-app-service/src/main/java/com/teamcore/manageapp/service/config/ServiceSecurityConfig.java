package com.teamcore.manageapp.service.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.encoding.PlaintextPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

import javax.sql.DataSource;


@Configuration
@EnableWebSecurity
@Order(2)
public class ServiceSecurityConfig extends WebSecurityConfigurerAdapter {

    private DataSource dataSource;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        final String usersByUsernameQuery = "select c_email, c_password, c_enabled from t_users " +
                "where c_email = ?";
        final String authoritiesByUsernameQuery = "select t_users.c_email, t_user_groups.c_name " +
                "from t_users join t_user_groups on t_users.c_user_group_id = t_user_groups.c_group_id " +
                "where t_users.c_email = ?";

        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery(usersByUsernameQuery)
                .authoritiesByUsernameQuery(authoritiesByUsernameQuery)
                .passwordEncoder(new Pbkdf2PasswordEncoder("", 200000, 512));
    }

}
