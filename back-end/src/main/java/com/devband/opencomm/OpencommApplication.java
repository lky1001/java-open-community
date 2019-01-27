package com.devband.opencomm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.config.EnableWebFlux;

@EnableWebFlux
@SpringBootApplication
public class OpencommApplication {

    public static void main(String[] args) {
        SpringApplication.run(OpencommApplication.class, args);
    }

}

