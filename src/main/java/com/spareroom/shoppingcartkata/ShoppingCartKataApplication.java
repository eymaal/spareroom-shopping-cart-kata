package com.spareroom.shoppingcartkata;

import com.spareroom.shoppingcartkata.model.SpecialPrice;
import com.spareroom.shoppingcartkata.util.ProductDataset;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.spareroom.shoppingcartkata.model.Product;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class ShoppingCartKataApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShoppingCartKataApplication.class, args);
        System.out.println(ProductDataset.getInstance());
    }

}
