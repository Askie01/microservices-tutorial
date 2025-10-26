package com.askie01.apigateway.configuration;

import com.askie01.apigateway.filter.FilterUtility;
import com.askie01.apigateway.filter.ResponseTraceFilter;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ResponseTraceFilterConfiguration {

    @Bean
    public GlobalFilter responseTraceFilter(FilterUtility filterUtility) {
        return new ResponseTraceFilter(filterUtility);
    }
}
