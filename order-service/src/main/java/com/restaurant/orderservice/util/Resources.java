package com.restaurant.orderservice.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.restaurant.orderservice.model.MenuConfig;

import java.io.File;
import java.io.IOException;

public class Resources {

    public static MenuConfig getMenuListResource(String url) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File("/app/" + url);
        return mapper.readValue(file, new TypeReference<>() {
        });
    }
}
