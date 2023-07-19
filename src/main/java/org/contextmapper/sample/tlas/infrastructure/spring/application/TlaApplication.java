package org.contextmapper.sample.tlas.infrastructure.spring.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableAutoConfiguration
@Configuration
@ComponentScan("org.contextmapper.sample.tlas")
@EnableJpaRepositories("org.contextmapper.sample.tlas.infrastructure.jpa")
@EntityScan("org.contextmapper.sample.tlas.infrastructure.jpa")
public class TlaApplication {

    public static void main(String[] args) {
        SpringApplication.run(TlaApplication.class, args);
    }

}
