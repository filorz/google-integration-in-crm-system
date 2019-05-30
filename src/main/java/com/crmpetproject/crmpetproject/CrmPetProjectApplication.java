package com.crmpetproject.crmpetproject;

import com.crmpetproject.crmpetproject.configuration.initializer.DataInitializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CrmPetProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(CrmPetProjectApplication.class, args);
    }

    @Bean(initMethod = "init")
    public DataInitializer initTestData() {
        return new DataInitializer();
    }

}
