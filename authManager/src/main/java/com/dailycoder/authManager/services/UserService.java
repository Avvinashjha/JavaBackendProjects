package com.dailycoder.authManager.services;

import com.dailycoder.authManager.dto.UserDetailsDTO;
import com.dailycoder.authManager.entities.User;
import com.dailycoder.authManager.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, AuthenticationManager authenticationManager, JwtService jwtService) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }


    public User register(User newUser) {
        String encodedPassword = bCryptPasswordEncoder.encode(newUser.getPassword());
        newUser.setPassword(encodedPassword);
        return userRepository.save(newUser);
    }

    public String login(User requestUser) {
        User user = userRepository.findByUsername(requestUser.getUsername());
        if (user != null && bCryptPasswordEncoder.matches(requestUser.getPassword(), user.getPassword())) {
            return "Login successful";
        } else {
            return "Invalid username or password";
        }
    }

    public String verify(User user){
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                user.getUsername(), user.getPassword()
        ));
        if (authenticate.isAuthenticated()) {
            return jwtService.generateToken(user);
        } else {
            return "User does not exist";
        }
    }

    public UserDetailsDTO getUserDetails(String token) {
        String username = jwtService.extractUsername(token);
        User user = userRepository.findByUsername(username);
        return new UserDetailsDTO(user.getId(), user.getUsername());
    }
}
