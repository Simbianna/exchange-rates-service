package ru.simbial.exchangeratesservice.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CurrencyChangesReactionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    CurrencyChangesReactionController controller;

    @Test
    public void testReturnStatusOk() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/reaction/USD"))
                .andExpect(status().isOk());
    }

}