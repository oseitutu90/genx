package com.app.genx.service;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class DataProcessor {
    public static Map<String, String> processData (){
        final Map<String, String> dump = Map.of("Shane", "Blue");
        return dump;
    }
}
