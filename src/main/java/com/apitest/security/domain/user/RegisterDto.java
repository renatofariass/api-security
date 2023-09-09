package com.apitest.security.domain.user;

public record RegisterDto(String login, String password, UserRole userRole) {
}
