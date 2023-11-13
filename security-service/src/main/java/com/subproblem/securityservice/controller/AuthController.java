package com.subproblem.securityservice.controller;

import com.subproblem.securityservice.dto.AuthenticationDto;
import com.subproblem.securityservice.dto.RegistrationDto;
import com.subproblem.securityservice.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/security")
public class AuthController {

    private final AuthService authService;


    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegistrationDto registrationDto) {
        return authService.registerUser(registrationDto);
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody AuthenticationDto authenticationDto) {
        return authService.authenticateUser(authenticationDto);
    }

    @PostMapping("/validate")
    public ResponseEntity<?> validateToken(@RequestParam String token) {
        return authService.validateJwtToken(token);
    }
}
