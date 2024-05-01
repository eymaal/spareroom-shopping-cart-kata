package com.spareroom.shoppingcartkata.controller;


import com.spareroom.shoppingcartkata.model.SubTotalResponse;
import com.spareroom.shoppingcartkata.service.CheckoutService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("api/v1")
@RestController
@AllArgsConstructor
public class CheckoutController {

    private final CheckoutService checkoutService;

    public ResponseEntity<SubTotalResponse> getSubTotal() {
        try{
            return checkoutService.checkout();
        } catch(Exception e) {
            return ResponseEntity.badRequest().build();
        }

    }
}
