package com.spareroom.shoppingcartkata.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema
public class SubTotalResponse {
    Double subTotal;
}
