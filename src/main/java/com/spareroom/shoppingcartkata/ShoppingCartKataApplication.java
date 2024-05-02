package com.spareroom.shoppingcartkata;

import com.spareroom.shoppingcartkata.model.ProductResponse;
import com.spareroom.shoppingcartkata.service.CheckoutService;
import com.spareroom.shoppingcartkata.util.ProductDataset;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



import java.util.*;

@SpringBootApplication
@Slf4j
public class ShoppingCartKataApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShoppingCartKataApplication.class, args);
        log.info(String.format("Server started at %s", System.currentTimeMillis()));
        CheckoutService service = new CheckoutService();
        try {
            List<ProductResponse> list = service.consumeDataSource();
            System.out.println(list);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        System.out.println(ProductDataset.getInstance());
    }

}
