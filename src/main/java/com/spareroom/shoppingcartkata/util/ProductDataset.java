package com.spareroom.shoppingcartkata.util;


import com.spareroom.shoppingcartkata.model.Product;
import com.spareroom.shoppingcartkata.model.SpecialPrice;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
/**
 * This class is a container which effectively acts as the Product Dataset.
 * The ProductDataset is implemented using the Singleton Design pattern and is a HashSet of Products.
 * The class contains a static block which is executed when the server starts.
 * This static block creates the initial products necessary for our Shopping Cart kata and adds it to the hashset.
 * For a full-fledged application/API, this could be shifted to a JSON/ database instead.
 */
public final class ProductDataset {
    private static Map<String, Product> INSTANCE = new HashMap<>();

    private ProductDataset(){}

    /**
     * This static block, in essence, creates the initial products necessary for our Shopping Cart kata.
     * For a full-fledged application/API, this could be shifted to a JSON/ database instead.
     */
    static {
        Product product = Product
                .builder()
                .itemCode("A")
                .unitPrice(50.00)
                .specialPrice(SpecialPrice
                        .builder().price(140.00).quantity(3).build())
                .build();
        ProductDataset.addProduct(product);
        product = Product
                .builder()
                .itemCode("B")
                .unitPrice(35.00)
                .specialPrice(SpecialPrice
                        .builder().price(60.00).quantity(2).build())
                .build();
        ProductDataset.addProduct(product);
        product = Product
                .builder()
                .itemCode("C")
                .unitPrice(25.00)
                .build();
        ProductDataset.addProduct(product);
        product = Product
                .builder()
                .itemCode("D")
                .unitPrice(12.00)
                .build();
        ProductDataset.addProduct(product);
    }

    public static Map<String, Product> getInstance() {
        return INSTANCE;
    }

    public static Product getProduct(String itemCode) {
        return INSTANCE.getOrDefault(itemCode, null);
    }

    public static void addProduct(Product product) {
        INSTANCE.put(product.getItemCode(),product);
    }

    public static void removeProduct(Product product) {
        INSTANCE.remove(product.getItemCode());
    }

}
