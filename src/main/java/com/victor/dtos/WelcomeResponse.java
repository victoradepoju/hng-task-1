package com.victor.dtos;

public record WelcomeResponse(
        String client_ip,
        double latitude,
        double longitude,
        String location,
        String greeting
) {
}
