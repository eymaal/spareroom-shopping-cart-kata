package com.spareroom.shoppingcartkata.service;

import java.util.ArrayList;
import java.util.List;
import com.spareroom.shoppingcartkata.model.ProductResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.commons.util.ReflectionUtils;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.util.ReflectionTestUtils;


@ExtendWith(MockitoExtension.class)
public class CheckoutServiceTest {


    @InjectMocks
    private static CheckoutService checkoutService;

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


    @Test
    public void consumeDataSourceTest() throws Exception {
        ReflectionTestUtils.setField(checkoutService, "url", url);
        Assertions.assertEquals(checkoutService.consumeDataSource(),responseList);
//        [ProductResponse(code=A, quantity=3), ProductResponse(code=B, quantity=3), ProductResponse(code=C, quantity=1), ProductResponse(code=D, quantity=2)]
    }
}
