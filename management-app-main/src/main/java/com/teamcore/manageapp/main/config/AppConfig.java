package com.teamcore.manageapp.main.config;

import com.teamcore.manageapp.service.config.ServiceConfigsMarker;
import com.teamcore.manageapp.web.config.WebConfigsMarker;
import com.teamcore.manageapp.web.controllers.ControllersMarker;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@ComponentScan(basePackageClasses = {AppSecurityConfig.class, WebConfigsMarker.class, ServiceConfigsMarker.class, ControllersMarker.class})
public class AppConfig { }
