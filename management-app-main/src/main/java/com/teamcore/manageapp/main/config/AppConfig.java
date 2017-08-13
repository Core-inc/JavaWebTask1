package com.teamcore.manageapp.main.config;

import com.teamcore.manageapp.service.config.DatabaseConfig;
import com.teamcore.manageapp.web.config.WebMvcConfig;
import com.teamcore.manageapp.web.config.WebSecurityConfig;
import com.teamcore.manageapp.web.controllers.ControllersMarker;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;


@Configuration
@ComponentScan(basePackageClasses = {ControllersMarker.class})
@Import({DatabaseConfig.class, WebSecurityConfig.class, WebMvcConfig.class})
public class AppConfig { }
