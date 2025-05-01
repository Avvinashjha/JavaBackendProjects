package com.dailycoder.libraryManagement.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Tag(
        name = "Library Management System Monitoring",
        description = "Monitoring operations for the Library Management System"
)
@RestController
@RequestMapping("/api/v1/lms/monitoring")
public class MonitoringController {

   @Operation(
        summary = "Get monitoring information",
        description = "Returns monitoring information for the Library Management System"
    )
   @GetMapping("/health")
    public String getMonitoringInfo() {
        return "Monitoring information for the Library Management System";
    }

}
