package com.spareroom.shoppingcartkata.service;

import com.spareroom.shoppingcartkata.model.Product;
import com.spareroom.shoppingcartkata.model.ProductResponse;
import com.spareroom.shoppingcartkata.model.SubTotalResponse;
import com.spareroom.shoppingcartkata.util.DatasourceConsumerUtil;
import com.spareroom.shoppingcartkata.util.ProductDataset;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;


@Component
@Data
@Service
@AllArgsConstructor
@NoArgsConstructor
public class CheckoutService {
    //TODO move url to application.properties.
    String url = "https://spareroom.github.io/recruitment/docs/cart-kata/data-set-1.json";

    public ResponseEntity<SubTotalResponse> checkout() throws Exception{
        List<ProductResponse> quantityList = DatasourceConsumerUtil.consumeDatasource(url);
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
            qty = item.getQuantity() % product. getSpecialPrice().getQuantity();
        }
        itemTotal+= qty * product.getUnitPrice();
        return itemTotal;
    }

//    public List<ProductResponse> consumeDataSource() throws Exception {
//        RestTemplate template = new RestTemplate();
//        try {
//            return Arrays.asList(Objects.requireNonNull(template.getForObject(url, ProductResponse[].class)));
//        } catch (Exception e) {
//            throw new Exception(e.getMessage());
//        }
//    }

}
