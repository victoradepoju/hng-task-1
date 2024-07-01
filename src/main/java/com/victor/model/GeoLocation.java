package com.victor.model;

public record GeoLocation (
        String ip,
        double latitude,
        double longitude
) {
}
