package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class UrlsList {
    /**
     * link - url
     */
    private Map<String, String> urls;
}
