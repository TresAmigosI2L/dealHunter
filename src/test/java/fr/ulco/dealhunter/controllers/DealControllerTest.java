package fr.ulco.dealhunter.controllers;

import fr.ulco.dealhunter.models.dto.DealResponseDto;
import fr.ulco.dealhunter.services.DealService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// FIXME: This test fails when DB is off, it shouldn't
@SpringBootTest
@ExtendWith(MockitoExtension.class)
class DealControllerTest {
    @InjectMocks
    private DealController dealController;
    @Mock
    private DealService dealService;
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(dealController).build();
    }

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
        DealResponseDto deal = new DealResponseDto();
        deal.setId(id);

        when(dealService.getAll()).thenReturn(List.of(deal));

        mockMvc.perform(get("/api/deals"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$[0].id").value(id.toString()));
    }
}