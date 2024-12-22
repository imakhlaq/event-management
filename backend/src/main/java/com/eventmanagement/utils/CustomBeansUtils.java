package com.eventmanagement.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class CustomBeansUtils {

    @Bean
    public RestClient restClient() {
        return RestClient.builder().build();
    }
}


