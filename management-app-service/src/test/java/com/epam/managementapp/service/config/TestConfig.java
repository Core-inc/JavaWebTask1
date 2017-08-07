package com.epam.managementapp.service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;


@Configuration
@ComponentScan(value = {"com.epam.managementapp.service.dao.project.impl"})
public class TestConfig {

    private static final String PROPERTY_NAME_DATABASE_DRIVER = "org.postgresql.Driver";
    private static final String PROPERTY_NAME_DATABASE_URL = "jdbc:postgresql://localhost:5432/testDataBase";
    private static final String PROPERTY_NAME_DATABASE_USERNAME = "postgres";
    private static final String PROPERTY_NAME_DATABASE_PASSWORD = "123";

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName(PROPERTY_NAME_DATABASE_DRIVER);
        dataSource.setUrl(PROPERTY_NAME_DATABASE_URL);
        dataSource.setUsername(PROPERTY_NAME_DATABASE_USERNAME);
        dataSource.setPassword(PROPERTY_NAME_DATABASE_PASSWORD);

        return dataSource;
    }
    /**
     * Transaction manager for the configured datasource.
     *
     * @return Transaction manager.
     */
    @Bean
    public DataSourceTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate(DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }


}
