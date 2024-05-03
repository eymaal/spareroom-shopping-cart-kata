package com.spareroom.shoppingcartkata.util;

import com.spareroom.shoppingcartkata.model.ProductResponse;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DatasourceConsumerUtilTest {

    private static List<ProductResponse> responseList;
    private static String ValidUrl;
    private static String invalidUrl;
    private static ProductResponse productResponse;

    @BeforeAll
    public static void createResponseList() {
        responseList = new ArrayList<>();
        responseList.add(ProductResponse.builder().code("A").quantity(3).build());
        responseList.add(ProductResponse.builder().code("B").quantity(3).build());
        responseList.add(ProductResponse.builder().code("C").quantity(1).build());
        responseList.add(ProductResponse.builder().code("D").quantity(2).build());
        ValidUrl = "https://spareroom.github.io/recruitment/docs/cart-kata/data-set-1.json";
        invalidUrl = "invalid";
        productResponse = ProductResponse.builder().code("A").quantity(1).build();
    }

    @DisplayName("Test Datasource consumption")
    @Test
    public void consumeDatasourceTest() throws Exception {
        assertEquals(DatasourceConsumerUtil.consumeDatasource(ValidUrl), responseList);
    }

    @DisplayName("Test datasource consumption with invalid url")
    @Test
    public void consumeDatasourceTest_InvalidUrl() {
        Exception exception = assertThrows(Exception.class, () -> DatasourceConsumerUtil.consumeDatasource(invalidUrl));
        assertEquals(exception.getMessage(), "Datasource URL is not Valid!");
    }

    //ToDo add tests for negative values validation for response values
    @DisplayName("Test datasource with negative quantity")
    @Test
    public void validateItemTest_NegativeQuantity() {
        productResponse.setQuantity(-2);
        ShoppingCartException exception = assertThrows(ShoppingCartException.class, () -> DatasourceConsumerUtil.validateItem(productResponse));
        assertEquals(exception.getDescription(), "Datasource contains negative quantity");
        productResponse.setQuantity(1);
    }

    @DisplayName("Test datasource with blank code")
    @Test
    public void validateItemTest_BlankCode() {
        productResponse.setCode("");
        ShoppingCartException exception = assertThrows(ShoppingCartException.class, () -> DatasourceConsumerUtil.validateItem(productResponse));
        assertEquals(exception.getDescription(), "Datasource contains invalid product code");
        productResponse.setCode("A");
    }

    @DisplayName("Test datasource with code non-existent in dataset")
    @Test
    public void validateItemTest_InvalidCode() {
        productResponse.setCode("G");
        ShoppingCartException exception = assertThrows(ShoppingCartException.class, () -> DatasourceConsumerUtil.validateItem(productResponse));
        assertEquals(exception.getDescription(), "Product G does not exist in dataset");
        productResponse.setCode("A");
    }
}
