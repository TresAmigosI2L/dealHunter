package fr.ulco.dealhunter.controllers;

import fr.ulco.dealhunter.models.entities.DealEntity;
import fr.ulco.dealhunter.repositories.DealRepository;
import fr.ulco.dealhunter.repositories.UserRepository;
import fr.ulco.dealhunter.services.AuthService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.*;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false) // addFilters to false : disable auth for testing purposes
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = DealControllerTest.TestConfig.class)
class DealControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private DealRepository dealRepository;

    @InjectMocks
    private DealController dealController;

    @MockBean
    private AuthService authService;

    @EnableJpaRepositories
    @TestConfiguration
    public static class TestConfig {
        @Bean
        public DealRepository dealRepository() {
            return Mockito.mock(DealRepository.class);
        }

        @Bean
        public UserRepository userRepository() {
            return Mockito.mock(UserRepository.class);
        }
    }

    @Test
    void getDealsEmpty() throws Exception {
        when(dealRepository.findAll()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/deals"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    void getDeals() throws Exception{
        DealEntity dealEntity = mockFakeDealEntity();
        UUID id = dealEntity.getId();
        when(dealRepository.findAll()).thenReturn(List.of(dealEntity));

        mockMvc.perform(get("/api/deals"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$[0].id").value(id.toString()))
                .andExpect(jsonPath("$[0].title").value("title"))
                .andExpect(jsonPath("$[0].active").value(true));
    }

    @Test
    void getDeal() throws Exception {
        DealEntity dealEntity = mockFakeDealEntity();
        UUID id = dealEntity.getId();
        when(dealRepository.findById(id)).thenReturn(Optional.of(dealEntity));

        mockMvc.perform(get("/api/deals/"+id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("id").value(id.toString()))
                .andExpect(jsonPath("title").value("title"))
                .andExpect(jsonPath("active").value(true));
    }

    @Test
    void deleteDeal() throws Exception{
        DealEntity dealEntity = mockFakeDealEntity();
        UUID id = dealEntity.getId();

        final var request = MockMvcRequestBuilders.delete("/api/deals/"+id);

        mockMvc.perform(request)
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$").doesNotExist())
                .andExpect(jsonPath("id").doesNotExist())
                .andExpect(jsonPath("title").doesNotExist())
                .andExpect(jsonPath("active").doesNotExist());
    }

    @Test
    void updateDeal() throws Exception{
        DealEntity dealEntity = mockFakeDealEntity();
        UUID id = dealEntity.getId();
        when(dealRepository.findById(id)).thenReturn(Optional.of(dealEntity));

        final var request = MockMvcRequestBuilders
                .put("/api/deals/"+id)
                .content("{\"title\":\"new title\",\"active\":false}")
                .contentType("application/json");

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("id").value(id.toString()))
                .andExpect(jsonPath("title").value("new title"))
                .andExpect(jsonPath("active").value(false));
    }

    @Test
    void createDeal() throws Exception{
        DealEntity dealEntity = mockFakeDealEntity();
        when(dealRepository.save(dealEntity)).thenReturn(dealEntity);

        when(authService.getUsernameOfAuthenticatedUser()).thenReturn("28aeb0e7-2f09-42e6-b44f-6009e6baeb0c:maxime.vitse@decathlon.com");


        mockMvc.perform(post("/api/deals").
                        content("{\"title\":\"title\",\"active\":true}")
                        .contentType("application/json")
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("title").value("title"))
                .andExpect(jsonPath("active").value(true));
    }


    private DealEntity mockFakeDealEntity() {
        UUID id = UUID.randomUUID();

        DealEntity dealEntity = new DealEntity();
        dealEntity.setId(id);
        dealEntity.setTitle("title");
        dealEntity.setActive(true);

        return dealEntity;
    }
}