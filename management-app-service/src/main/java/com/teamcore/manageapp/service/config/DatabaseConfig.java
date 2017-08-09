package com.teamcore.manageapp.service.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Configuration
public class DatabaseConfig {
    @Bean
    @Autowired
    public DataSourceTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public DataSource dataSource() throws IOException {
        BasicDataSource driver = new BasicDataSource();

        Properties config = new Properties();
        InputStream in = new ClassPathResource("db/db.properties").getInputStream();
        config.load(in);

        driver.setDriverClassName(config.getProperty("dbDriver"));
        driver.setUrl(config.getProperty("dbURL"));
        driver.setUsername(config.getProperty("dbUser"));
        driver.setPassword(config.getProperty("dbPassword"));

        return driver;
    }

    @Bean
    @Autowired
    public NamedParameterJdbcTemplate namedJdbcTemplate(DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }

    @Bean
    @Autowired
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}
