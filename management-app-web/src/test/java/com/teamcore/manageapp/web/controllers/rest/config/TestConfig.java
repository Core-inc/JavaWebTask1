package com.teamcore.manageapp.web.controllers.rest.config;

import com.teamcore.manageapp.service.config.ServiceConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = {ServiceConfig.class})
public class TestConfig {
}
