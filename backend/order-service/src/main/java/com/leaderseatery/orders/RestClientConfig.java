package com.leaderseatery.orders;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig{

    @Bean
    public RestClient catalogRestClient(
        @Value("${app.catalog.base-url}") String catalogUrl){

            return RestClient.builder().baseUrl(catalogUrl).build();

        }
}