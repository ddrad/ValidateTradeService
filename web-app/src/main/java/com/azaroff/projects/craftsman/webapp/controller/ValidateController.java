package com.azaroff.projects.craftsman.webapp.controller;

import com.azaroff.projects.craftsman.webapp.model.Customer;
import com.azaroff.projects.craftsman.webapp.utils.validate.helper.ValidateHelper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
        List<Map<String, List<Customer>>> reply = validateHelper.validate(inputParameters);
        return objectMapper.writeValueAsString(reply);
    }

}