package com.restaurant.orderservice.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.restaurant.orderservice.model.MenuConfig;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;

public class Resources {

    public static MenuConfig getMenuListResource(String url) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        File file = new ClassPathResource(url).getFile();
        return mapper.readValue(file, new TypeReference<>() {
        });
    }
}
