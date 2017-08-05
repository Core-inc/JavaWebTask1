package com.teamcore.manageapp.main.config;

import com.teamcore.manageapp.service.config.ServiceSecurityConfig;
import com.teamcore.manageapp.web.config.WebSecurityConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
public class AppSecurityConfig {

   @Configuration
   @EnableWebSecurity
   public static class AppWebSecurityConfig extends WebSecurityConfig {}

   @Configuration
   @EnableWebSecurity
   public static class AppServiceSecurityConfig extends ServiceSecurityConfig {}
}
