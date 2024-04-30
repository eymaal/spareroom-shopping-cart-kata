package com.spareroom.shoppingcartkata.controller;


import com.spareroom.shoppingcartkata.model.SubTotalResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("api/v1")
@RestController
public class CheckoutController {

    public ResponseEntity<SubTotalResponse> getSubTotal() {
        try{
            return ResponseEntity.ok().build();
        } catch(Exception e) {
            return ResponseEntity.badRequest().build();
        }

    }
}
