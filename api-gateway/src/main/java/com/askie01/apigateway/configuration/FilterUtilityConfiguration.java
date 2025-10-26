package com.askie01.apigateway.configuration;

import com.askie01.apigateway.filter.FilterUtility;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterUtilityConfiguration {

    @Bean
    public FilterUtility filterUtility() {
        return new FilterUtility();
    }
}
