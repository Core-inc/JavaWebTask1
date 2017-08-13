package com.teamcore.manageapp.service.config;

import com.teamcore.manageapp.service.dao.DaoMarker;
import com.teamcore.manageapp.service.utils.TestUtilsMarker;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackageClasses = {DaoMarker.class, TestUtilsMarker.class})
@Import({DatabaseConfig.class, ServiceSecurityConfig.class, ServiceLoggingAspect.class})
public class TestServiceConfig {

}
