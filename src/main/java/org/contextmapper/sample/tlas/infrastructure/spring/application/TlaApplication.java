package org.contextmapper.sample.tlas.infrastructure.spring.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@EnableAutoConfiguration
@Configuration
@ComponentScan("org.contextmapper.sample.tlas")
public class TlaApplication {

    public static void main(String[] args) {
        SpringApplication.run(TlaApplication.class, args);
    }

}
