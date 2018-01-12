package com.azaroff.projects.craftsman.webapp.utils.validate.impl;

import com.azaroff.projects.craftsman.webapp.utils.validate.Validate;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ValidateHelper {

    public boolean isValidateDate(LocalDate cal, Validate vbd) {
        return vbd.isValid(cal);
    }

    public boolean isValidateString(String str, Validate vbd) {
        return vbd.isValid(str);
    }
}
