package com.leaderseatery.auth;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController{

    private final AuthService authService;

    public AuthController(AuthService authService){
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(
        @Valid @RequestBody RegisterRequest request){

            RegisterResponse response = authService.register(request);

            return ResponseEntity.status(HttpStatus.CREATED).body(authService.register(request));
        }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(

        @Valid @RequestBody LoginRequest request){

            return ResponseEntity.ok(authService.login(request));
        }
}