package com.askie01.apigateway.configuration;

import com.askie01.apigateway.filter.FilterUtility;
import com.askie01.apigateway.filter.RequestTraceFilter;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RequestTraceFilterConfiguration {

    @Bean
    public GlobalFilter requestTraceFilter(FilterUtility filterUtility) {
        return new RequestTraceFilter(filterUtility);
    }
}
