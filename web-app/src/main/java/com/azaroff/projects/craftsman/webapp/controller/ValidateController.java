package com.azaroff.projects.craftsman.webapp.controller;

import com.azaroff.projects.craftsman.webapp.model.Customer;
import com.azaroff.projects.craftsman.webapp.model.CustomerList;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Dmitry Azarov on 12.012018.
 */

@RestController
@RequestMapping("/app-validator")
public class ValidateController {

    @RequestMapping(value = "/validate", method = RequestMethod.POST)
    public String add(@RequestBody String data) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        Map<String, List<Customer>> inputParameters = objectMapper.readValue(data,
                new TypeReference<Map<String, List<Customer>>>() {
                });

        Map<String, List<Customer>> result = validate(inputParameters);
        return objectMapper.writeValueAsString(result);
    }

    @RequestMapping(value = "/get-validate", method = RequestMethod.GET)
    public String get() throws JsonProcessingException {
        Map<String, String> result = new HashMap<>();
        result.put("Get Result", "Successful");
        return new ObjectMapper().writeValueAsString(result);
    }

    private Map<String, List<Customer>> validate(Map<String, List<Customer>> params) {
        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");

        Map<String, List<Customer>> reply = params.entrySet()
                .stream()
                .map(m -> {
                    return m.getValue().stream()
                            .filter(c -> c.getValueDate() != null)
                            .filter(c -> {
                                DateTime valueDate = formatter.parseDateTime(c.getValueDate());
                                DateTime tradeDate = formatter.parseDateTime(c.getTradeDate());
                                return tradeDate.isBefore(valueDate);
                            })
                            .collect(Collectors.toList());
                })
                .collect (Collectors.toMap(e-> {
                    return UUID.randomUUID().toString();
                        }, m-> new ArrayList<Customer>())
                );

        return params;
    }

}