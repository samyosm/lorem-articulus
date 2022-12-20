package com.samyosm.loremarticulus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class LoremArticulusApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoremArticulusApplication.class, args);
    }

}
