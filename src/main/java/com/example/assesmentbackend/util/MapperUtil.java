package com.example.assesmentbackend.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class MapperUtil {

    public static String writeToString(Object object) {
        ObjectMapper objectMapper = new ObjectMapper();
        String json;
        try {
            json = objectMapper.writeValueAsString(object);
            return json;
        } catch (JsonProcessingException e) {
            log.error(e.toString());
            return null;
        }
    }

    public static String camelCaseToKebabCase(String input) {
        String result = input.replaceAll("([a-z])([A-Z])", "$1-$2");
        return result.toLowerCase();
    }
}
