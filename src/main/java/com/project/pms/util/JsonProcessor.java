package com.project.pms.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class JsonProcessor {
    public static String convertObjectToJson(Object object) {
        String jsonString = "";
        try {
            jsonString = new ObjectMapper().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonString;
    }

    public static String convertListOfObjectsToJson(List<?> objects) {
        String jsonString = "";
        try {
            jsonString = new ObjectMapper().writeValueAsString(objects);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonString;
    }
}
