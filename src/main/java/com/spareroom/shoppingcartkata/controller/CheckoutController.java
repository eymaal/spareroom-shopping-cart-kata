package com.spareroom.shoppingcartkata.controller;


import com.spareroom.shoppingcartkata.model.SubTotalResponse;
import com.spareroom.shoppingcartkata.service.CheckoutService;
import com.spareroom.shoppingcartkata.util.ShoppingCartException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name="Checkout")
public class CheckoutController {

    private final CheckoutService checkoutService;

    @Operation(
            description = "GET Endpoint for querying subTotal",
            summary = "This endpoint returns the sub total amount based on the number and type of objects obtained by consuming the given datasource.",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = { @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = SubTotalResponse.class)) }
                    ),
                    @ApiResponse(
                            description = "Bad Request",
                            responseCode = "400",
                            content = { @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ShoppingCartException.class)) }
                    )
            }
    )
    @GetMapping("/checkout")
    public ResponseEntity getSubTotal() {
        try{
            return checkoutService.checkout();
        } catch(Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body(ShoppingCartException.builder().message(e.getMessage()).build());
        }
    }
}
