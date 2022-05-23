package com.example.version4;

import com.example.version4.config.StorageProperties;
import com.example.version4.service.StorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class Version4Application {

    public static void main(String[] args) {
        SpringApplication.run(Version4Application.class, args);
    }

    @Bean
    CommandLineRunner init(StorageService storageService){
        return (args -> {
            storageService.init();
        });
    }

}
