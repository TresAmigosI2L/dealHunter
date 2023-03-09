package fr.ulco.dealhunter.controllers;

import fr.ulco.dealhunter.models.entities.DealEntity;
import fr.ulco.dealhunter.repositories.DealRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import java.nio.charset.StandardCharsets;
import java.util.*;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = DealControllerTest.TestConfig.class)
class DealControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private DealRepository dealRepository;

    @InjectMocks
    private DealController dealController;

    @EnableJpaRepositories
    @TestConfiguration
    public static class TestConfig {
        @Bean
        public DealRepository dealRepository() {
            return Mockito.mock(DealRepository.class);
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

        final var basicPayload = Base64.getEncoder()
                .encodeToString("admin:admin".getBytes(StandardCharsets.UTF_8));
        final var request = MockMvcRequestBuilders.get("/api/deals")
                .header("Authorization", "Basic " + basicPayload);

        mockMvc.perform(request)
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

        final var basicPayload = Base64.getEncoder()
                .encodeToString("admin:admin".getBytes(StandardCharsets.UTF_8));
        final var request = MockMvcRequestBuilders.get("/api/deals/"+id)
                .header("Authorization", "Basic " + basicPayload);

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("id").value(id.toString()))
                .andExpect(jsonPath("title").value("title"))
                .andExpect(jsonPath("active").value(true));
    }

    private DealEntity mockFakeDealEntity() {
        UUID id = UUID.randomUUID();

        DealEntity dealEntity = new DealEntity();
        dealEntity.setId(id);
        dealEntity.setTitle("title");
        dealEntity.setActive(true);

        when(dealRepository.findAll()).thenReturn(Arrays.asList(dealEntity));
        when(dealRepository.findById(id)).thenReturn(Optional.of(dealEntity));

        return dealEntity;
    }
}