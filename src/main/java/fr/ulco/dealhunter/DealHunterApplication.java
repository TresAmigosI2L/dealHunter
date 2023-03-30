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
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

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
            createAxelUser(userService);
            authenticatedAs("28aeb0e7-2f09-42e6-b44f-6009e6baeb0c:axel.lebas@decathlon.com","xmn");
            createFakeDeal(dealService);

        };
    }

    private static void createFakeDeal(DealService dealService) {
        CreateDealRequestDto createDealRequestDto = new CreateDealRequestDto();
        createDealRequestDto.setTitle("Bose headphones QC45");
        createDealRequestDto.setActive(true);
        createDealRequestDto.setImageUrl("https://assets.bose.com/content/dam/Bose_DAM/Web/consumer_electronics/global/products/headphones/qc45/product_silo_images/QC45_PDP_Ecom-Gallery-B02.png/_jcr_content/renditions/cq5dam.web.320.320.png");
        createDealRequestDto.setOriginalPrice(349.99);
        createDealRequestDto.setDiscountPrice(251.06);
        createDealRequestDto.setDealUrl("https://www.amazon.fr/Bose-Bluetooth-R%C3%A9duction-QuietComfort-Microphone/dp/B098FKXT8L/");
        dealService.create(createDealRequestDto);
    }

    private static void createAxelUser(UserService userService) {
        CreateUserRequestDto createUserRequestDto = new CreateUserRequestDto();
        createUserRequestDto.setUsername("axel.lebas@decathlon.com");
        createUserRequestDto.setPassword("xmn");
        createUserRequestDto.setConfirmPassword("xmn");
        userService.create(createUserRequestDto);
    }

    private void authenticatedAs(String username, String password) {
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(username, password));
    }
}
