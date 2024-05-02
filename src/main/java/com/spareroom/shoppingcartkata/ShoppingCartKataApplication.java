package com.spareroom.shoppingcartkata;

import com.spareroom.shoppingcartkata.model.ProductResponse;
import com.spareroom.shoppingcartkata.service.CheckoutService;
import com.spareroom.shoppingcartkata.util.ProductDataset;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@SpringBootApplication
@Slf4j
public class ShoppingCartKataApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShoppingCartKataApplication.class, args);
        log.info(String.format("Server started at %s", LocalDateTime.now().toString()));
        CheckoutService service = new CheckoutService();
        try {
            List<ProductResponse> list = service.consumeDataSource();
            System.out.println(list);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

}
