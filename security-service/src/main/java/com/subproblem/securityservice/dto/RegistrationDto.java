package com.subproblem.securityservice.dto;

public record RegistrationDto(
        String username,
        String email,
        String password
) {
}
