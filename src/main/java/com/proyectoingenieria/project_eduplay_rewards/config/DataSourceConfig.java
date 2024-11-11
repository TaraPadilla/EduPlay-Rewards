package com.proyectoingenieria.project_eduplay_rewards.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.services.ssm.SsmClient;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    private final AwsParameterStoreConfig awsParameterStoreConfig;
    private final SsmClient ssmClient;

    public DataSourceConfig(AwsParameterStoreConfig awsParameterStoreConfig, SsmClient ssmClient) {
        this.awsParameterStoreConfig = awsParameterStoreConfig;
        this.ssmClient = ssmClient;
    }

    @Bean
    public DataSource dataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(awsParameterStoreConfig.getParameter(ssmClient, "/project/db_url"));
        config.setUsername(awsParameterStoreConfig.getParameter(ssmClient, "/project/db_username"));
        config.setPassword(awsParameterStoreConfig.getParameter(ssmClient, "/project/db_password"));
        config.setDriverClassName("com.mysql.cj.jdbc.Driver");
        return new HikariDataSource(config);
    }
}
