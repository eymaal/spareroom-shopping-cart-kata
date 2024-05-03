package com.spareroom.shoppingcartkata.model;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
public class Product {

    @NonNull
    String itemCode;
    @NonNull
    Double unitPrice;
    SpecialPrice specialPrice;

    @Override
    public String toString() {
        return "Product{" + "itemCode='" + itemCode + '\'' + ", unitPrice=" + unitPrice + (specialPrice==null? "": ", specialPrice=" + specialPrice) + '}';
    }
}
