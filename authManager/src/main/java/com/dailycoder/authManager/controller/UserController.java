package com.dailycoder.authManager.controller;

import com.dailycoder.authManager.dto.UserDetailsDTO;
import com.dailycoder.authManager.dto.UserLoginDTO;
import com.dailycoder.authManager.dto.UserRegisterDTO;
import com.dailycoder.authManager.entities.User;
import com.dailycoder.authManager.repository.UserRepository;
import com.dailycoder.authManager.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Tag(name = "User Authentication and Authorization", description = "User auth operations")
@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;


    @Operation(
        summary = "Register a new user",
        description = "Registers a new user in the system"
    )
    @PostMapping("/register")
    public User registerUser(@RequestBody UserRegisterDTO user) {
        User newUser = new User(user.getUsername(), user.getPassword());
        return userService.register(newUser);
    }

    @Operation(
        summary = "Login a user",
        description = "Logs in a user and returns a success message"
    )
    @PostMapping("/login")
    public String loginUser(@RequestBody UserLoginDTO user) {
        User requestUser = new User(user.getUsername(), user.getPassword());
        return userService.login(requestUser);
    }


    @Operation(
        summary = "Verify a user",
        description = "Verifies a user and returns a JWT token"
    )
    @PostMapping("/verify")
    public String verifyUser(@RequestBody UserLoginDTO user) {
        User requestUser = new User(user.getUsername(), user.getPassword());
        return userService.verify(requestUser);
    }

    @Operation(
            summary = "Get user details",
            description = "Returns the details of the user"
    )
    @GetMapping("/details")
    public UserDetailsDTO getUserDetails(@RequestHeader String token) {
        return userService.getUserDetails(token);
    }

}
