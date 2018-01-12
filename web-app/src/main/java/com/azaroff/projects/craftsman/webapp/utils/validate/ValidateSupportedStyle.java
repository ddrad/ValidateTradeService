package com.azaroff.projects.craftsman.webapp.utils.validate;

import org.springframework.stereotype.Component;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class ValidateSupportedStyle {

    public boolean isSupportedCustomer(String style){
        return Stream.of(SupportedCustomer.values())
                .map(Enum::name)
                .filter(v-> v.equals(style))
                .collect(Collectors.toList()).size() > 0 ? true : false;
    }

    private enum SupportedCustomer {
        EUROPEAN, AMERICAN
    }
}
