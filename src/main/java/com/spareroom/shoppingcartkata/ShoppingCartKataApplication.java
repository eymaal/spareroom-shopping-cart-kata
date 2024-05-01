package com.spareroom.shoppingcartkata;

import com.spareroom.shoppingcartkata.model.ProductResponse;
import com.spareroom.shoppingcartkata.service.CheckoutService;
import com.spareroom.shoppingcartkata.util.ProductDataset;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



import java.util.*;

@SpringBootApplication
public class ShoppingCartKataApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShoppingCartKataApplication.class, args);
        CheckoutService service = new CheckoutService();
        List<ProductResponse> list = service.consumeDataSource();
        System.out.println(list);
        System.out.println(ProductDataset.getInstance());
    }

}
