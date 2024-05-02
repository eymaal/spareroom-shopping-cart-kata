package com.spareroom.shoppingcartkata.util;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;

@Builder
@JsonIgnoreProperties({"cause", "stackTrace", "suppressed", "localizedMessage"})
public class ShoppingCartException extends RuntimeException{
    private String message;
    private String description;
}
