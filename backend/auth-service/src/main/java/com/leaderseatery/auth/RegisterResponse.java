package com.leaderseatery.auth;

public record RegisterResponse(

    Long userId,
    String fullName,
    String email,
    String role,
    String message
){}