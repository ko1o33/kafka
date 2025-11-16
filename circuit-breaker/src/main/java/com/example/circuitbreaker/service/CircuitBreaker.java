package com.example.circuitbreaker.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Service
@RestController
public class CircuitBreaker {

    @GetMapping("/fallback/module1")
    public String module1Fallback() {
        return "This message is fallback";
    }

    @GetMapping("/fallback/module2")
    public String module2Fallback() {
        return "This message is fallback";
    }
}
