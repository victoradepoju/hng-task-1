package com.victor.controller;

import com.victor.dtos.WelcomeResponse;
import com.victor.service.WelcomeService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/hello")
public class AppController {

    private final WelcomeService welcomeService;

    @Autowired
    public AppController(WelcomeService welcomeService) {
        this.welcomeService = welcomeService;
    }

    @GetMapping
    public ResponseEntity<WelcomeResponse> welcome(
            @RequestParam(name = "visitor_name") String name
    ) {
        return ResponseEntity.ok(welcomeService.welcome());
    }

    @GetMapping("/ip")
    public String getIpAddress(
            HttpServletRequest request
    ) {
        return "Client IP: " + request.getRemoteAddr();
    }
}
