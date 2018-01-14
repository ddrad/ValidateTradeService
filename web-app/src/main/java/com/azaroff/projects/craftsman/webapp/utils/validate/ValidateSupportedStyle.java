package com.azaroff.projects.craftsman.webapp.utils.validate;

import com.azaroff.projects.craftsman.webapp.utils.validate.constant.SupportedStyle;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class ValidateSupportedStyle {

    public boolean isSupportedStyle(String style) {
        return Stream.of(SupportedStyle.values())
                .map(Enum::name)
                .filter(v -> v.equals(style))
                .collect(Collectors.toList()).size() > 0 ? true : false;
    }
}
