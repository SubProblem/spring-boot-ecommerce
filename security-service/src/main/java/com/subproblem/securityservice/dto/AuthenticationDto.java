package com.subproblem.securityservice.dto;

public record AuthenticationDto(
        String email,
        String password
) {
}
