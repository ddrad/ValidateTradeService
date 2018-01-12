package com.azaroff.projects.craftsman.webapp.controller;

import com.azaroff.projects.craftsman.webapp.model.Customer;
import com.azaroff.projects.craftsman.webapp.utils.validate.ValidateBusinessDay;
import com.azaroff.projects.craftsman.webapp.utils.validate.ValidateSupportedCustomer;
import com.azaroff.projects.craftsman.webapp.utils.validate.impl.ValidateHelper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Dmitry Azarov on 12.012018.
 */

@RestController
@RequestMapping("/app-validator")
public class ValidateController {

    @Autowired
    private ValidateHelper validateHelper;
    @Autowired
    private ValidateBusinessDay vbd;
    @Autowired
    private ValidateSupportedCustomer vsc;

    @RequestMapping(value = "/validate", method = RequestMethod.POST)
    public String add(@RequestBody String data) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        Map<String, List<Customer>> inputParameters = objectMapper.readValue(data,
                new TypeReference<Map<String, List<Customer>>>() {
                });

        Map<String, List<Customer>> failedValidateValDateTradeDate = validateValueDate(inputParameters);
        Map<String, List<Customer>> failedValidateBusinessDay = validateBusinessDay(inputParameters);
        Map<String, List<Customer>> failedSupportedCustomer = validateSupportedCustomer(inputParameters);

        Map<String, List<Customer>> itemHasStyle = validateStyle(inputParameters);
        if (!CollectionUtils.isEmpty(itemHasStyle)) {
            // need to validate
            // - American option style will have in addition the excerciseStartDate, which has to be after the trade date but before the expiry date
            //- expiry date and premium date shall be before delivery date
        }

        List<Map<String, List<Customer>>> reply = new ArrayList<>();
        reply.add(failedValidateValDateTradeDate);
        reply.add(failedValidateBusinessDay);
        reply.add(failedSupportedCustomer);
        return objectMapper.writeValueAsString(reply);
    }

    @RequestMapping(value = "/get-validate", method = RequestMethod.GET)
    public String get() throws JsonProcessingException {
        Map<String, String> result = new HashMap<>();
        result.put("Get Result", "Successful");
        return new ObjectMapper().writeValueAsString(result);
    }

    /**
     * @param params
     * @return failed records of list where Value Date is before Trade Date
     */
    private Map<String, List<Customer>> validateValueDate(Map<String, List<Customer>> params) {
        Map<String, List<Customer>> reply = params.entrySet()
                .stream()
                .collect(Collectors.toMap(m -> m.getKey() + " - incorrect: Value Date is before Trade Date",
                        m -> m.getValue().stream()
                                .filter(c -> c.getValueDate() != null)
                                .peek(c -> {
                                    c.setValueDateDate(LocalDate.parse(c.getValueDate()));
                                    c.setTradeDateDate(LocalDate.parse(c.getTradeDate()));
                                })
                                .filter(c -> {
                                    return c.getValueDateDate().isBefore(c.getTradeDateDate());
                                })
                                .collect(Collectors.toList()))
                );
        return reply;
    }

    /**
     * @param params
     * @return failed records of list where trade was on work off day
     */
    private Map<String, List<Customer>> validateBusinessDay(Map<String, List<Customer>> params) {
        Map<String, List<Customer>> reply = params.entrySet()
                .stream()
                .collect(Collectors.toMap(m -> m.getKey() + " - Not Business Days",
                        m -> m.getValue().stream()
                                .filter(c -> c.getValueDate() != null)
                                .peek(c -> {
                                    c.setValueDateDate(LocalDate.parse(c.getValueDate()));
                                    c.setTradeDateDate(LocalDate.parse(c.getTradeDate()));
                                })
                                .filter(c -> {
                                    return !validateHelper.isValidateDate(c.getValueDateDate(),
                                            ld -> vbd.isBusinessDay((LocalDate) ld));
                                })
                                .collect(Collectors.toList()))
                );
        return reply;
    }

    /**
     * @param params
     * @return failed records of list which have not supported Customer field
     */
    private Map<String, List<Customer>> validateSupportedCustomer(Map<String, List<Customer>> params) {
        Map<String, List<Customer>> reply = params.entrySet()
                .stream()
                .collect(Collectors.toMap(m -> m.getKey() + " Not Suported Customer",
                        m -> m.getValue().stream()
                                .filter(c -> {
                                    return !validateHelper.isValidateString(c.getCustomer(),
                                            n -> vsc.isSupportedCustomer((String) n));
                                })
                                .collect(Collectors.toList()))
                );
        return reply;
    }

    /**
     * @param params
     * @return record of list which have Style field
     */
    private Map<String, List<Customer>> validateStyle(Map<String, List<Customer>> params) {
        Map<String, List<Customer>> reply = params.entrySet()
                .stream()
                .collect(Collectors.toMap(m -> m.getKey(),
                        m -> m.getValue().stream()
                                .filter(c -> c.getStyle() != null)
                                .filter(c -> {
                                    return validateHelper.isValidateString(c.getStyle(),
                                            n -> vsc.isSupportedCustomer((String) n));
                                })
                                .collect(Collectors.toList()))
                );
        return reply;
    }

}