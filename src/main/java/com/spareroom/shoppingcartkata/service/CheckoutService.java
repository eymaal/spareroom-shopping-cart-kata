package com.spareroom.shoppingcartkata.service;

import com.spareroom.shoppingcartkata.model.Product;
import com.spareroom.shoppingcartkata.model.ProductResponse;
import com.spareroom.shoppingcartkata.model.SubTotalResponse;
import com.spareroom.shoppingcartkata.util.ProductDataset;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component
@Service
@Data
public class CheckoutService {
    //TODO move url to application.properties.
    String url = "https://spareroom.github.io/recruitment/docs/cart-kata/data-set-1.json";

    public ResponseEntity<SubTotalResponse> checkout() throws Exception{
        List<ProductResponse> quantityList = consumeDataSource();
        double subTotal=0.0;
        for(ProductResponse item : quantityList) {
            Product product = ProductDataset.getProduct(item.getCode());
            if(product == null) throw new Exception(String.format("Item %s does not exist", item.getCode()));
            subTotal+= getItemPrice(item, product);
        }
        return ResponseEntity.ok().body(SubTotalResponse.builder().subTotal(subTotal).build());
    }

    private double getItemPrice(ProductResponse item, Product product) {
        double itemTotal = 0.0;
        int qty = item.getQuantity();
        if(product.getSpecialPrice()!=null) {
            qty = item.getQuantity() / product.getSpecialPrice().getQuantity();
            itemTotal+= qty * product.getSpecialPrice().getPrice();
            qty = item.getQuantity() % product.getSpecialPrice().getQuantity();
        }
        itemTotal+= qty * product.getUnitPrice();
        return itemTotal;
    }

    public List<ProductResponse> consumeDataSource() {
        RestTemplate template = new RestTemplate();
        List<ProductResponse> productResponseList = Arrays.asList(template.getForObject(url, ProductResponse[].class));
        return productResponseList;
    }

}
