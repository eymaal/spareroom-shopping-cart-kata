package com.spareroom.shoppingcartkata;

import com.spareroom.shoppingcartkata.model.ProductResponse;
import com.spareroom.shoppingcartkata.util.DatasourceConsumerUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import java.time.LocalDateTime;
import java.util.*;

@SpringBootApplication
@Slf4j
public class ShoppingCartKataApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShoppingCartKataApplication.class, args);
        log.info(String.format("Server started at %s", LocalDateTime.now().toString()));
        try {
            List<ProductResponse> list = DatasourceConsumerUtil.consumeDatasource("https://spareroom.github.io/recruitment/docs/cart-kata/data-set-1.json");
            System.out.println(list);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

}
