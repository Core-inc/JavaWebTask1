package com.teamcore.manageapp.service.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.io.IOException;

@Configuration
@PropertySource("classpath:db/db.properties")
public class DatabaseConfig {

    @Autowired
    private Environment env;

    @Bean
    @Autowired
    public DataSourceTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public DataSource dataSource() throws IOException {
        BasicDataSource driver = new BasicDataSource();

        driver.setDriverClassName(env.getProperty("dbDriver"));
        driver.setUrl(env.getProperty("dbURL"));
        driver.setUsername(env.getProperty("dbUser"));
        driver.setPassword(env.getProperty("dbPassword"));

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
