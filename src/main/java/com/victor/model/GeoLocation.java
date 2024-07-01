package com.victor.model;

public record GeoLocation (
        String ip,
        String city,
        double latitude,
        double longitude
) {
}
