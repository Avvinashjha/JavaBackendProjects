package com.dailycoder.authManager.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Auth Welcome", description = "Auth Welcome operations")
@RestController
@RequestMapping("/api/v1/auth/welcome")
public class AuthController {

    @Operation(
        summary = "Welcome message",
        description = "Returns a welcome message for the Auth Manager API"
    )
    @GetMapping("")
    public String welcome() {
        return "Welcome to the Auth Manager API!";
    }

    @Operation(
        summary = "Get CSRF token",
        description = "Returns the CSRF token for the current session"
    )
    @GetMapping("/csrf")
    public CsrfToken getToken(HttpServletRequest request) {
        return (CsrfToken) request.getAttribute("_csrf");
    }
}
