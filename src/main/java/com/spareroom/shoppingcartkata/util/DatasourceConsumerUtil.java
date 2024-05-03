package com.spareroom.shoppingcartkata.util;

import com.spareroom.shoppingcartkata.ShoppingCartKataApplication;
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
            return Arrays.asList(Objects.requireNonNull(template.getForObject(url, ProductResponse[].class)));
        } catch (Exception e) {
            throw ShoppingCartException.builder().message(e.getMessage()).build();
        }
    }

    private static boolean isUrlValid(String url) {
        UrlValidator validator = new UrlValidator();
        return validator.isValid(url);
    }
}
