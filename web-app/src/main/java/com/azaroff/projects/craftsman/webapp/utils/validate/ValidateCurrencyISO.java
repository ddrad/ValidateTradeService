package com.azaroff.projects.craftsman.webapp.utils.validate;

import com.azaroff.projects.craftsman.webapp.utils.validate.constant.SupportedStyle;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Currency;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class ValidateCurrencyISO {

    public boolean isSupportedIsoCode(String isoCurCodes) {
        List<String> inputIsoCode = Arrays.asList(isoCurCodes.split("(?<=\\G...)"));
        return Currency.getAvailableCurrencies().stream().map(c -> c.getCurrencyCode())
                .collect(Collectors.toList()).containsAll(inputIsoCode);
    }

}
