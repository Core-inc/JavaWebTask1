package com.teamcore.manageapp.service.config;

import com.teamcore.manageapp.service.utils.ConfigException;
import com.teamcore.manageapp.service.utils.ConfigReader;
import com.teamcore.manageapp.service.utils.FileConfigReader;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.io.IOException;

@Configuration
public class DatabaseConfig {

    @Bean
    @Autowired
    public DataSourceTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    @Autowired
    public DataSource dataSource(ConfigReader cfgReader) {
        BasicDataSource driver = new BasicDataSource();

        //read config options
        try {
            driver.setDriverClassName(cfgReader.getOption("dbDriver"));
            driver.setUrl(cfgReader.getOption("dbURL"));
            driver.setUsername(cfgReader.getOption("dbUser"));
            driver.setPassword(cfgReader.getOption("dbPassword"));

         } catch (ConfigException e) {
            throw new BeanCreationException("db driver settings setup failed", e);
         }

        return driver;
    }

    @Bean
    @Autowired
    public NamedParameterJdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }

    @Bean
    public ConfigReader plainTextConfigReader() {
        try {
            return new FileConfigReader("db/db.conf", (bytes) -> bytes);
        } catch (IOException e) {
            throw new BeanCreationException("db FileConfigReader creation failed", e);
        }
    }
}
