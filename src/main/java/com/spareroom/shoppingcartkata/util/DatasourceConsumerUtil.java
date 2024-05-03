package com.spareroom.shoppingcartkata.util;

import com.spareroom.shoppingcartkata.model.ProductResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Slf4j
public class DatasourceConsumerUtil {

    public static List<ProductResponse> consumeDatasource(String url) throws Exception {
        if(!isUrlValid(url)) {
            log.error(String.format("URL [%s] is not valid", url));
            throw new Exception("Datasource URL is not Valid!");
        }
        RestTemplate template = new RestTemplate();
        try {
            List<ProductResponse> productResponseList = Arrays.asList(Objects.requireNonNull(template.getForObject(url, ProductResponse[].class)));
            productResponseList.forEach(DatasourceConsumerUtil::validateItem);
            return productResponseList;
        } catch (ShoppingCartException e) {
            throw e;
        } catch (Exception e) {
            throw ShoppingCartException.builder().message(e.getMessage()).build();
        }
    }

    public static void validateItem(ProductResponse response) throws ShoppingCartException {
        if (response.getQuantity() < 0) {
            throw new ShoppingCartException("Invalid details", "Datasource contains negative quantity");
        } else if (response.getCode().isBlank()) {
            throw new ShoppingCartException("Invalid details", "Datasource contains invalid product code");
        } else if (!ProductDataset.contains(response.getCode())) {
            throw new ShoppingCartException("Invalid details",
                    String.format("Product %s does not exist in dataset", response.getCode()));
        }
    }

    private static boolean isUrlValid(String url) {
        UrlValidator validator = new UrlValidator();
        return validator.isValid(url);
    }
}
