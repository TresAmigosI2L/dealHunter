package fr.ulco.dealhunter;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class DealHunterApplication {

    public static void main(String[] args) {
        SpringApplication.run(DealHunterApplication.class, args);
    }

    //FAKE DATA FOR BDD : you could put service in params
    @Bean
    CommandLineRunner run() {
        return args -> {
            // create a default account for demo purpose
        };
    }
}
