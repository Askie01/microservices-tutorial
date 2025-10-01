package com.askie01.configurationserver.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestClient;

@Configuration
public class LocalHostRestClientConfiguration {

    @Bean
    public RestClient restClient(Environment environment) {
        final String localHostUrl = getLocalHostUrl(environment);
        return RestClient.builder()
                .baseUrl(localHostUrl)
                .build();
    }

    private String getLocalHostUrl(Environment environment) {
        final String port = environment.getProperty("server.port");
        return String.format("http://localhost:%s", port);
    }
}
