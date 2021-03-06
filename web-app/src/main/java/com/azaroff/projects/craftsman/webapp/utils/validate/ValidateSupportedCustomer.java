package com.azaroff.projects.craftsman.webapp.utils.validate;

import com.azaroff.projects.craftsman.webapp.utils.validate.constant.SupportedCustomer;
import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class ValidateSupportedCustomer {

    public boolean isSupportedCustomer(String customerName){
        return Stream.of(SupportedCustomer.values())
                .map(Enum::name)
                .filter(v-> v.equals(customerName))
                .collect(Collectors.toList()).size() > 0 ? true : false;
    }

}
