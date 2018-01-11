package com.azaroff.projects.craftsman.webapp.controller;

import com.azaroff.projects.craftsman.webapp.model.Customer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Dmitry Azarov on 12.012018.
 */

@RestController
@RequestMapping("/app-validator")
public class ValidateController {

    @RequestMapping(value = "/validate", method = RequestMethod.POST)
    public String add(@RequestBody String data) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Customer> myObjects = objectMapper.readValue(data, objectMapper.getTypeFactory()
                .constructCollectionType(List.class, Customer.class));

        Map<String, String> result = new HashMap<>();
        result.put("Poet Result", "Successful");
        return objectMapper.writeValueAsString(result);
    }

    @RequestMapping(value = "/get-validate", method = RequestMethod.GET)
    public String get() throws JsonProcessingException  {
        Map<String, String> result = new HashMap<>();
        result.put("Get Result", "Successful");
        return new ObjectMapper().writeValueAsString(result);
    }

}