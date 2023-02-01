package ru.skypro.homework;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"ru.skypro.homework"})
public class SpringConfig {

    @Bean
    public DataSource getDataSource()
    {
        DriverManagerDataSource dataSource =  new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:5432/graduateWork");
        dataSource.setUsername("command");
        dataSource.setPassword("1234567890");
        return dataSource;
    }

}
