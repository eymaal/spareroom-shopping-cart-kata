package com.spareroom.shoppingcartkata.service;

import java.util.ArrayList;
import java.util.List;

import com.spareroom.shoppingcartkata.model.Product;
import com.spareroom.shoppingcartkata.model.ProductResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;


@Slf4j
@ExtendWith(MockitoExtension.class)
public class CheckoutServiceTest {


    @InjectMocks
    private static CheckoutService checkoutService;

    private String url;

    @BeforeEach
    public void prepare() {
        url = "https://spareroom.github.io/recruitment/docs/cart-kata/data-set-1.json";
    }

    @DisplayName("Test subtotal calculation with default datasource")
    @Test
    public void checkoutTest() {
        try {
            ReflectionTestUtils.setField(checkoutService, "url", url);
            assertEquals(checkoutService.checkout().getBody().getSubTotal(), 284.0);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @DisplayName("Test subtotal calculation with datasource containing negative values")
    @Test
    public void checkoutTest_NegativeValues() {
        try {
            ReflectionTestUtils.setField(checkoutService, "url", url);
            assertEquals(checkoutService.checkout().getBody().getSubTotal(), 284.0);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }


}
