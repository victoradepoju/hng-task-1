package com.victor.dtos;

public record WelcomeResponse(
        String client_ip,
        String location,
        String greeting
) {
}
