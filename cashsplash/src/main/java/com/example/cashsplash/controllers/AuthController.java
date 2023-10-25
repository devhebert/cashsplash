package com.example.cashsplash.controllers;

import com.example.cashsplash.dtos.login.AuthRequestDTO;
import com.example.cashsplash.models.User;
import com.example.cashsplash.repositories.UserRepository;
import com.example.cashsplash.services.login.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @PostMapping
    public String login(@RequestBody AuthRequestDTO request) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(request.username(), request.password());
        this.authenticationManager.authenticate(authentication);
        User user = this.userRepository.findByUsername(request.username()).orElseThrow();
        return this.jwtService.createToken(user);
    }
}
