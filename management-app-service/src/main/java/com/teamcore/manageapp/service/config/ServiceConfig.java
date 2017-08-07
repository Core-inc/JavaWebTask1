package com.teamcore.manageapp.service.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({DatabaseConfig.class, ServiceSecurityConfig.class})
public class ServiceConfig {
}
