package fr.ulco.dealhunter;

import fr.ulco.dealhunter.models.dto.auth.CreateUserRequestDto;
import fr.ulco.dealhunter.models.dto.deal.CommentDealRequestDto;
import fr.ulco.dealhunter.models.dto.deal.CreateDealRequestDto;
import fr.ulco.dealhunter.models.dto.deal.DealResponseDto;
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
            createUser(userService, "axel.lebas@decathlon.com", "xmn");
            authenticatedAs("28aeb0e7-2f09-42e6-b44f-6009e6baeb0c:axel.lebas@decathlon.com","xmn");
            DealResponseDto deal = createFakeDeal(dealService);
            DealResponseDto deal2 = createFakeDeal(dealService);

            createUser(userService, "maxime.vitse@decathlon.com", "admin123");
            authenticatedAs("28aeb0e7-2f09-42e6-b44f-6009e6baeb0c:maxime.vitse@decathlon.com","admin123");

            createFakeComment(dealService, deal);
            addXFakeVotes(dealService, deal, 10);
        };
    }

    private void addXFakeVotes(DealService dealService, DealResponseDto deal, int numberVotes) {
        dealService.voteDeal(deal.getId(),numberVotes);
    }

    private static void createUser(UserService userService, String username, String password) {
        CreateUserRequestDto createUserRequestDto = new CreateUserRequestDto();
        createUserRequestDto.setUsername(username);
        createUserRequestDto.setPassword(password);
        createUserRequestDto.setConfirmPassword(password);
        userService.create(createUserRequestDto);
    }

    private void authenticatedAs(String username, String password) {
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(username, password));
    }

    private DealResponseDto createFakeDeal(DealService dealService) {
        CreateDealRequestDto createDealRequestDto = new CreateDealRequestDto();
        createDealRequestDto.setTitle("Bose headphones QC45");
        createDealRequestDto.setActive(true);
        createDealRequestDto.setImageUrl("https://assets.bose.com/content/dam/Bose_DAM/Web/consumer_electronics/global/products/headphones/qc45/product_silo_images/QC45_PDP_Ecom-Gallery-B02.png/_jcr_content/renditions/cq5dam.web.320.320.png");
        createDealRequestDto.setOriginalPrice(349.99);
        createDealRequestDto.setDiscountPrice(251.06);
        createDealRequestDto.setDealUrl("https://www.amazon.fr/Bose-Bluetooth-R%C3%A9duction-QuietComfort-Microphone/dp/B098FKXT8L/");

        DealResponseDto dealResponseDto = dealService.create(createDealRequestDto);

        return dealResponseDto;
    }

    private static void createFakeComment(DealService dealService, DealResponseDto deal) {
        CommentDealRequestDto commentDealRequestDto = new CommentDealRequestDto();
        commentDealRequestDto.setMessage("J'ai le 700, il a l'air bien aussi.");
        dealService.addComment(deal.getId(), commentDealRequestDto);
    }
}
