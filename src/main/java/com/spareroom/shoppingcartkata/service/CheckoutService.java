package com.spareroom.shoppingcartkata.service;

import com.spareroom.shoppingcartkata.model.Product;
import com.spareroom.shoppingcartkata.model.ProductResponse;
import com.spareroom.shoppingcartkata.model.SubTotalResponse;
import com.spareroom.shoppingcartkata.util.DatasourceConsumerUtil;
import com.spareroom.shoppingcartkata.util.ProductDataset;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
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

    private String url;

    @Value("${spareroom.datasource.url}")
    public void setUrl(String url) {
        this.url = url;
    }

    public ResponseEntity<SubTotalResponse> checkout() throws Exception{
        List<ProductResponse> quantityList = DatasourceConsumerUtil.consumeDatasource(url);
        double subTotal = getSubtotal(quantityList);
        return ResponseEntity.ok().body(SubTotalResponse.builder().subTotal(subTotal).build());
    }

    public double getSubtotal(List<ProductResponse> quantityList) throws Exception {
        double subTotal = 0.0;
        for(ProductResponse item : quantityList) {
            Product product = ProductDataset.getProduct(item.getCode());
            if(product == null) throw new Exception(String.format("Item %s does not exist", item.getCode()));
            subTotal+= getItemPrice(item, product);
        }
        return subTotal;
    }

    private double getItemPrice(ProductResponse item, Product product) {
        double itemTotal = 0.0;
        int qty = Math.max(0, item.getQuantity());
        double unitPrice = Math.max(0, product.getUnitPrice());
        if(product.getSpecialPrice()!=null) {
            double specialPrice = Math.max(0, product.getSpecialPrice().getPrice());
            int specialQty = Math.max(0, product.getSpecialPrice().getQuantity());
            itemTotal = itemTotal + specialPrice * (qty / specialQty);
            qty = qty % specialQty;
        }
        itemTotal+= qty * unitPrice;
        return itemTotal;
    }

}
