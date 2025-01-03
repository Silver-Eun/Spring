package com.spring.songjava.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SchedulerCronConfiguration {

    private final GlobalConfig config;

    public SchedulerCronConfiguration(GlobalConfig config) {
        this.config = config;
    }

    @Bean
    public String schedulerCronExample1() {
        return config.getScheduleCronExample1();
    }
}
