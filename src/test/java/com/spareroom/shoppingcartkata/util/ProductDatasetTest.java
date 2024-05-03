package com.spareroom.shoppingcartkata.util;


import com.spareroom.shoppingcartkata.model.Product;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProductDatasetTest {

    private static Product product;

    @BeforeAll
    public static void prepare() {
        product = Product.builder()
                .itemCode("Sample")
                .unitPrice(1.00)
                .build();
    }

    @DisplayName("Test add product")
    @Test
    public void addProductTest() {
        ProductDataset.addProduct(product);
        assertTrue(ProductDataset.contains("Sample"));
    }

    @DisplayName("Test remove product")
    @Test
    public void removeProductTest() {
        ProductDataset.removeProduct("Sample");
        assertFalse(ProductDataset.contains("Sample"));
    }

    @DisplayName("Test Price Dataset instance")
    @Test
    public void getInstanceTest() {
        assertNotNull(ProductDataset.getInstance());
    }
}
