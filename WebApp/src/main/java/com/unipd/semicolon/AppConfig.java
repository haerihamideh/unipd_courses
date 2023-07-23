package com.unipd.semicolon;

import com.unipd.semicolon.business.constants.Constants;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

public class AppConfig {
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl(Constants.URL_DB);
        dataSource.setUsername(Constants.USERNAME_DB);
        dataSource.setPassword(Constants.PASSWORD_DB);
        return dataSource;
    }
}