package com.spareroom.shoppingcartkata.model;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
public class SpecialPrice {

    @NonNull
    @Builder.Default private Integer quantity = 0;
    @NonNull
    @Builder.Default private Double price = 0.0;

}
