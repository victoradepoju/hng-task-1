package com.victor.service;

import com.victor.dtos.WelcomeResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WelcomeService {

    private final HttpServletRequest request;

    @Autowired
    public WelcomeService(HttpServletRequest request) {
        this.request = request;
    }

    public WelcomeResponse welcome() {

        String ipAddress = ipAddress();
        return null;
    }

    private String ipAddress() {
        return request.getRemoteAddr();
    }


}
