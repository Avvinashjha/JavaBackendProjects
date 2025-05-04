package com.springlab.cricInfo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HeathCheckController {

    @GetMapping("/api/v1/cricinfo/health")
    public String healthCheck() {
        return "OK";
    }
}
