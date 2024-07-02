package com.victor.model;

import java.util.Map;

public record Weather(
        String name,
        Map<String, Object> main
) {
}
