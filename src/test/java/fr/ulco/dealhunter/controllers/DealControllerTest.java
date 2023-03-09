package fr.ulco.dealhunter.controllers;

import fr.ulco.dealhunter.models.dto.DealResponseDto;
import fr.ulco.dealhunter.models.entities.DealEntity;
import fr.ulco.dealhunter.repositories.DealRepository;
import fr.ulco.dealhunter.services.DealService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import java.nio.charset.StandardCharsets;
import java.util.*;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// FIXME: This test fails when DB is off, it shouldn't
@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
class DealControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Mock
    private DealRepository dealRepository;

   // @Mock
    //private DealService dealService;

    @InjectMocks
    private DealController dealController;

    @Test
    void getDealsEmpty() throws Exception {
        mockMvc.perform(get("/api/deals"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").isEmpty());
    }

    // FIXME: This test fails, it shouldn't (mock on `dealService.getAll()` doesnt work)
    @Test
    void getDeals() throws Exception {
        UUID id = UUID.randomUUID();

        DealEntity dealEntity = new DealEntity();
        dealEntity.setId(id);
        dealEntity.setTitle("title");
        dealEntity.setActive(true);

        DealResponseDto dealResponseDto = new DealResponseDto();
        dealResponseDto.setId(id);
        dealResponseDto.setTitle("title");
        dealResponseDto.setActive(true);

        when(dealRepository.findAll()).thenReturn(Arrays.asList(dealEntity));
        //when(dealService.getAll()).thenReturn(Arrays.asList(dealResponseDto));


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
                .andExpect(content().json("[]"));
    }
}