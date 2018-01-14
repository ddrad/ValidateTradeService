package com.azaroff.projects.craftsman.webapp.controller;

import com.azaroff.projects.craftsman.webapp.model.Customer;
import com.azaroff.projects.craftsman.webapp.utils.validate.*;
import com.azaroff.projects.craftsman.webapp.utils.validate.constant.SupportedStyle;
import com.azaroff.projects.craftsman.webapp.utils.validate.impl.ValidateHelper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;

/**
 * Created by Dmitry Azarov on 12.012018.
 */

@RestController
@RequestMapping("/app-validator")
public class ValidateController {

   @Autowired
   private ValidateHelper validateHelper;

    @RequestMapping(value = "/validate", method = RequestMethod.POST)
    public String add(@RequestBody String data) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        Map<String, List<Customer>> inputParameters = objectMapper.readValue(data,
                new TypeReference<Map<String, List<Customer>>>() {
                });

        Map<String, List<Customer>> failedValidateValDateTradeDate = validateHelper.validateValueDate(inputParameters);
        Map<String, List<Customer>> failedValidateBusinessDay = validateHelper.validateBusinessDay(inputParameters);
        Map<String, List<Customer>> failedSupportedCustomer = validateHelper.validateSupportedCustomer(inputParameters);
        Map<String, List<Customer>> failedValidateISO = validateHelper.validateISO(inputParameters);
        Map<String, List<Customer>> failedValueDateByType = validateHelper.validateValueDateByProduct(inputParameters);
        List<Map<String, List<Customer>>> reply = new ArrayList<>();
        reply.add(failedValidateValDateTradeDate);
        reply.add(failedValidateBusinessDay);
        reply.add(failedSupportedCustomer);
        reply.add(failedValidateISO);
        reply.add(failedValueDateByType);
        Map<String, List<Customer>> itemHasStyle = validateHelper.validateStyle(inputParameters);
        if (!CollectionUtils.isEmpty(itemHasStyle)) {
            Map<String, List<Customer>> failedvalidateDatesOfStyle = validateHelper.validateDatesOfStyle(itemHasStyle);
            Map<String, List<Customer>> failedvalidateDatesOfAmericanStyle = validateHelper.validateDateOfAmericanStyle(itemHasStyle);
            reply.add(failedvalidateDatesOfStyle);
            reply.add(failedvalidateDatesOfAmericanStyle);
        }

        return objectMapper.writeValueAsString(reply);
    }



}