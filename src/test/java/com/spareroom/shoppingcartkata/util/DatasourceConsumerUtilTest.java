package com.spareroom.shoppingcartkata.util;

import com.spareroom.shoppingcartkata.model.ProductResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DatasourceConsumerUtilTest {

    private List<ProductResponse> responseList;
    private String url;

    @BeforeEach
    public void createResponseList() {
        responseList = new ArrayList<>();
        responseList.add(ProductResponse.builder().code("A").quantity(3).build());
        responseList.add(ProductResponse.builder().code("B").quantity(3).build());
        responseList.add(ProductResponse.builder().code("C").quantity(1).build());
        responseList.add(ProductResponse.builder().code("D").quantity(2).build());
        url = "https://spareroom.github.io/recruitment/docs/cart-kata/data-set-1.json";
    }

    @DisplayName("Test Datasource consumption")
    @Test
    public void consumeDatasourceTest() throws Exception {
        assertEquals(DatasourceConsumerUtil.consumeDatasource(url), responseList);
    }

    @DisplayName("Test datasource consumption with invalid url")
    @Test
    public void consumeDatasourceTest_InvalidUrl() throws Exception {
        Exception exception = assertThrows(Exception.class, () -> {
            DatasourceConsumerUtil.consumeDatasource("");
        });
        assertEquals(exception.getMessage(), "Datasource URL is not Valid!");
    }
}
