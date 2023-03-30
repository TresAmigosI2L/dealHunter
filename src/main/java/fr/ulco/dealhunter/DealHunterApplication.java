package fr.ulco.dealhunter;

import fr.ulco.dealhunter.models.dto.auth.CreateUserRequestDto;
import fr.ulco.dealhunter.models.dto.deal.CreateDealRequestDto;
import fr.ulco.dealhunter.services.DealService;
import fr.ulco.dealhunter.services.UserService;
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
    CommandLineRunner run(UserService userService, DealService dealService) {
        return args -> {
            // create a default account for front demo purpose
            CreateUserRequestDto createUserRequestDto = new CreateUserRequestDto();
            createUserRequestDto.setUsername("axel.lebas@decathlon.com");
            createUserRequestDto.setPassword("xmn");
            createUserRequestDto.setConfirmPassword("xmn");
            userService.create(createUserRequestDto);

            CreateDealRequestDto createDealRequestDto = new CreateDealRequestDto();
            createDealRequestDto.setTitle("Bose headphones QC45");
            createDealRequestDto.setActive(true);
            dealService.create(createDealRequestDto);

        };
    }
}
