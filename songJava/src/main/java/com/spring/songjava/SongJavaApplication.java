package com.spring.songjava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SongJavaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SongJavaApplication.class, args);
    }

}
