package com.proyectoingenieria.project_eduplay_rewards.config;

import org.springframework.context.annotation.Bean;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ssm.SsmClient;
import software.amazon.awssdk.services.ssm.model.GetParameterRequest;
import software.amazon.awssdk.services.ssm.model.GetParameterResponse;

public class ParameterStoreConfig {
    @Bean
    public SsmClient ssmClient() {
        return SsmClient.builder()
                .region(Region.US_EAST_1) // Cambia esto a la región donde están tus parámetros
                .credentialsProvider(DefaultCredentialsProvider.create())
                .build();
    }

    @Bean
    public String dbUrl(SsmClient ssmClient) {
        return getParameterValue(ssmClient, "/project/db_url");
    }

    @Bean
    public String dbUsername(SsmClient ssmClient) {
        return getParameterValue(ssmClient, "/project/db_username");
    }

    @Bean
    public String dbPassword(SsmClient ssmClient) {
        return getParameterValue(ssmClient, "/project/db_password");
    }

    private String getParameterValue(SsmClient ssmClient, String parameterName) {
        GetParameterRequest request = GetParameterRequest.builder()
                .name(parameterName)
                .withDecryption(true)
                .build();
        GetParameterResponse response = ssmClient.getParameter(request);
        return response.parameter().value();
    }

}
