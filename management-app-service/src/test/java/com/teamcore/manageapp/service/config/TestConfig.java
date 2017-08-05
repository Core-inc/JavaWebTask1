package com.teamcore.manageapp.service.config;

import com.teamcore.manageapp.service.dao.DaoMarker;
import com.teamcore.manageapp.service.utils.TestUtilsMarker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackageClasses = {DaoMarker.class, TestUtilsMarker.class, ServiceConfigsMarker.class })
public class TestConfig {

   @Bean
   public DataSourceTransactionManager transactionManager(DataSource dataSource) {
      return new DataSourceTransactionManager(dataSource);
   }
}
