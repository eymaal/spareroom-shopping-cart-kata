package com.spareroom.shoppingcartkata.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SubTotalResponse {
    Double subTotal;
}
