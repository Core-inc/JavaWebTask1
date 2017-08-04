package com.teamcore.manageapp.service.config;

import com.teamcore.manageapp.service.TestFactory;
import com.teamcore.manageapp.service.dao.user.UserDAOImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
//TODO create marker interfaces!!!!
@ComponentScan(basePackageClasses = {UserDAOImpl.class, TestFactory.class, DataBaseConfig.class })
public class TestConfig {

   @Bean
   public DataSourceTransactionManager transactionManager(DataSource dataSource) {
      return new DataSourceTransactionManager(dataSource);
   }
}
