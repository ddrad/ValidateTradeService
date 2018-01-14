package com.azaroff.projects.craftsman.webapp.utils.validate;

import com.azaroff.projects.craftsman.webapp.utils.validate.constant.SupportedCustomer;
import com.azaroff.projects.craftsman.webapp.utils.validate.constant.SupportedProduct;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class ValidateByProduct {

    public boolean isSpotOrForward(String productName) {
        return Stream.of(SupportedCustomer.values())
                .map(Enum::name)
                .filter(v -> productName.equals(SupportedProduct.Spot.name())
                        || productName.equals(SupportedProduct.Forward.name()))
               // .filter(v -> v.equals(productName))
                .collect(Collectors.toList()).size() > 0 ? true : false;
    }
}
