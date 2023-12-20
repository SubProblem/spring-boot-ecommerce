package com.subproblem.gateway.config;

import com.subproblem.gateway.dto.UserResponseDto;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;


import java.util.List;

@Component
public class AuthFilter extends AbstractGatewayFilterFactory<AuthFilter.Config> {

    private final WebClient.Builder webClient;


    public AuthFilter(WebClient.Builder webClient) {
        super(Config.class);
        this.webClient = webClient;
    }


    private List<String> permittedEndpoints = List.of(
            "/api/v1/security/register",
            "/api/v1/security/login"
    );

    @Override
    public GatewayFilter apply(AuthFilter.Config config) {
        return ((exchange, chain) -> {


            String jwtToken = extractToken(exchange.getRequest());


            if (checkEndpoint(exchange.getRequest())) {
                return chain.filter(exchange);
            }


            if (extractToken(exchange.getRequest()) == null) {
                throw new RuntimeException("Wrong information");
            }


            return webClient.build()
                    .post()
                    .uri("lb://SECURITY-SERVICE/api/v1/security/validate?token=" + jwtToken.substring(7))
                    .retrieve()
                    .bodyToMono(UserResponseDto.class)
                    .map(dto -> {
                        exchange.getRequest()
                                .mutate()
                                .header("auth-header-id", String.valueOf(dto.id()));
                        return exchange;
                    }).flatMap(chain::filter);

        });
    }


    public static class Config {

    }

    private String extractToken(ServerHttpRequest request) {
        return request.getHeaders().getFirst("Authorization");
    }

    private boolean checkEndpoint(ServerHttpRequest request) {
        return permittedEndpoints.stream()
                .anyMatch(request.getPath().toString()::startsWith);
    }
}
