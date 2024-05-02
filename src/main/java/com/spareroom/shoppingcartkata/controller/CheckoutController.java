package com.spareroom.shoppingcartkata.controller;


import com.spareroom.shoppingcartkata.model.SubTotalResponse;
import com.spareroom.shoppingcartkata.service.CheckoutService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@CrossOrigin
@RequestMapping("api/v1")
@RestController
@AllArgsConstructor
public class CheckoutController {

    private final CheckoutService checkoutService;

    @GetMapping("/checkout")
    public ResponseEntity getSubTotal() {
        try{
            return checkoutService.checkout();
        } catch(Exception e) {
            log.error(e.getMessage());
            return ResponseEntity
                    .badRequest()
                    .body(String.format("{\"message\":\"%s\"}", e.getMessage()));
        }
    }
}
