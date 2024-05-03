package com.spareroom.shoppingcartkata.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.spareroom.shoppingcartkata.service.CheckoutService;
import com.spareroom.shoppingcartkata.util.ShoppingCartException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Slf4j
@WebMvcTest(controllers = CheckoutController.class)
@AutoConfigureMockMvc(addFilters = false)
public class CheckoutControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CheckoutService checkoutService;
    @InjectMocks
    private CheckoutService service;

    @DisplayName("Test response success")
    @Test
    public void getSubTotalTest_Valid() {
        try {
            ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/checkout"));
            response.andExpect(MockMvcResultMatchers.status().isOk());
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @DisplayName("Test Bad Request")
    @Test
    public void getSubTotal_Invalid() {
        try {
            CheckoutService checkoutService = mock(CheckoutService.class);
            when(service.checkout()).thenThrow(new Exception());
            ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/checkout"));
            response.andExpect(MockMvcResultMatchers.status().isBadRequest());
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

}
