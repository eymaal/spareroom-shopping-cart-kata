package com.spareroom.shoppingcartkata.model;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.util.Objects;

@Data
@Builder
public class Product {

    @NonNull
    String itemCode;
    @NonNull
    Double unitPrice;
    SpecialPrice specialPrice;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(itemCode, product.itemCode) && Objects.equals(unitPrice, product.unitPrice) && Objects.equals(specialPrice, product.specialPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemCode);
    }

    @Override
    public String toString() {
        return "Product{" +
                "itemCode='" + itemCode + '\'' +
                ", unitPrice=" + unitPrice +
                (specialPrice==null? "": ", specialPrice=" + specialPrice) +
                '}';
    }
}
