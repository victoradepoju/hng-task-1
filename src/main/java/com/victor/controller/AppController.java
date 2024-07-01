package com.victor.controller;

import com.victor.dtos.WelcomeResponse;
import com.victor.service.WelcomeService;
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
            @RequestParam(name = "visitor_name", required = false) String visitor,
            @RequestHeader(value = "X-Forwarded-For", required = false) String forwardedFor
    ) {
        return ResponseEntity.ok(welcomeService.welcome(visitor, forwardedFor));
    }
}
