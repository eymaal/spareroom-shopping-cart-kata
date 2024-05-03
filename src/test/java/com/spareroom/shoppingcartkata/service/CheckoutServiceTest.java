package com.spareroom.shoppingcartkata.service;

import com.spareroom.shoppingcartkata.model.ProductResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@Slf4j
@ExtendWith(MockitoExtension.class)
public class CheckoutServiceTest {


    @InjectMocks
    private static CheckoutService checkoutService;

    private String url;
    private List<ProductResponse> dummyDatasource;

    @BeforeEach
    public void prepare() {
        dummyDatasource = new ArrayList<>();
        url = "https://spareroom.github.io/recruitment/docs/cart-kata/data-set-1.json";
    }

    @DisplayName("Test subtotal calculation with default datasource")
    @Test
    public void checkoutTest() {
        try {
            assertEquals(checkoutService.checkout().getBody().getSubTotal(), 284.0);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @DisplayName("Test subtotal calculation using another datasource")
    @Test
    public void getSubtotalTest() {
        dummyDatasource.add(ProductResponse.builder().code("A").quantity(7).build());
        try {
            assertEquals(checkoutService.getSubtotal(dummyDatasource), 330.0);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @DisplayName("Test subtotal calculation using another datasource")
    @Test
    public void getSubtotalTest2() {
        dummyDatasource.add(ProductResponse.builder().code("A").quantity(9).build());
        try {
            assertEquals(checkoutService.getSubtotal(dummyDatasource), 420);
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
