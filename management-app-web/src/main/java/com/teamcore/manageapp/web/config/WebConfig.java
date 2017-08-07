package com.teamcore.manageapp.web.config;

import com.teamcore.manageapp.service.services.ServicesMarker;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = {ServicesMarker.class})
public class WebConfig {
}
