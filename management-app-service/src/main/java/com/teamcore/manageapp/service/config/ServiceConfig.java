package com.teamcore.manageapp.service.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

@Configuration
@EnableAspectJAutoProxy
@Import({DatabaseConfig.class, ServiceSecurityConfig.class, LoggingAspect.class})
public class ServiceConfig {
}
