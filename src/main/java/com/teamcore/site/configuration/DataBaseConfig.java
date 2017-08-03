package com.teamcore.site.configuration;

import com.teamcore.site.utils.ConfigException;
import com.teamcore.site.utils.ConfigReader;
import com.teamcore.site.utils.FileConfigReader;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.io.IOException;

@Configuration
public class DataBaseConfig {

    @Bean
    public DataSource dataSource(ConfigReader cfgReader) {
        DriverManagerDataSource driver = new DriverManagerDataSource();

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
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public SimpleJdbcInsert simpleJdbcInsert(JdbcTemplate jdbcTemplate) {
        return new SimpleJdbcInsert(jdbcTemplate);
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
