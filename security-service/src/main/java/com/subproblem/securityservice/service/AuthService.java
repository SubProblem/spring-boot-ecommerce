package com.subproblem.securityservice.service;

import com.subproblem.securityservice.dto.AuthenticationDto;
import com.subproblem.securityservice.dto.RegistrationDto;
import com.subproblem.securityservice.dto.ValidationResponse;
import com.subproblem.securityservice.entity.User;
import com.subproblem.securityservice.jwt.JwtService;
import com.subproblem.securityservice.repository.UserRepository;
import com.subproblem.securityservice.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.subproblem.securityservice.entity.Role.USER;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public ResponseEntity<?> registerUser(RegistrationDto registrationDto) {

        if (userRepository.existsByEmail(registrationDto.email())) {
            return ResponseEntity.badRequest().body("Email already exists");
        }

        var user = User.builder()
                .username(registrationDto.username())
                .email(registrationDto.email())
                .password(passwordEncoder.encode(registrationDto.password()))
                .role(USER)
                .build();

        userRepository.save(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    public ResponseEntity<?> authenticateUser(AuthenticationDto authorizationDto) {

        String email = authorizationDto.email();
        String password = authorizationDto.password();

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(email, password);

        authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        var jwtToken = jwtService.generateJwtToken(authorizationDto.email());

        return ResponseEntity.ok(jwtToken);

    }

    public ResponseEntity<?> validateJwtToken(String token) {
        boolean isTokenValid = jwtService.validateJwtToken(token);

        if (!isTokenValid) {
            throw new RuntimeException("Authentication Failed");
        }

        var userEmail = jwtService.getEmailFromToken(token);

        var user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        var response = new ValidationResponse(
                user.getId(),
                user.getEmail()
        );

        return ResponseEntity.ok(response);
    }
}
