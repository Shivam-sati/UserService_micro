package com.proj.user.service.UserService.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Myconfig {
    @Bean
    //@LoadBalanced  // only if you’re using service names like "HOTEL-SERVICE"
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}

