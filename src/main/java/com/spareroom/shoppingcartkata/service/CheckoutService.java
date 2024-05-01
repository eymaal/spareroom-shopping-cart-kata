package com.spareroom.shoppingcartkata.service;

import com.spareroom.shoppingcartkata.model.ProductResponse;
import com.spareroom.shoppingcartkata.model.SubTotalResponse;
import com.spareroom.shoppingcartkata.util.ProductDataset;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
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

    String url = "https://spareroom.github.io/recruitment/docs/cart-kata/data-set-1.json";

    public ResponseEntity<SubTotalResponse> checkout() {
        consumeDataSource();
        return ResponseEntity.ok().build();
    }

    public List<ProductResponse> consumeDataSource() {
        RestTemplate template = new RestTemplate();
        List<ProductResponse> productResponseList = Arrays.asList(template.getForObject(url, ProductResponse[].class));
        return productResponseList;
    }

}
