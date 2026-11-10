package com.leaderseatery.auth;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AuthService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService( UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @Transactional
    public RegisterResponse register (RegisterRequest request){
        String email = request.email().trim().toLowerCase();

        if(userRepository.existsByEmail(email)){

            throw new ResponseStatusException(HttpStatus.CONFLICT, "An account with this email already exists");
        }

        User user = new User();

        user.setFullName(request.fullName().trim());
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setRole(User.Role.CUSTOMER);

        User savedUser = userRepository.save(user);

        return new RegisterResponse(
            savedUser.getUserId(),
            savedUser.getFullName(),
            savedUser.getEmail(),
            savedUser.getRole().name(),
            "Account registered successfully"
        );
    }

    public LoginResponse login(LoginRequest request){

        String email = request.email().trim().toLowerCase();

        User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid email or password"));

        if(!passwordEncoder.matches(request.password(), user.getPassword())){

            throw new ResponseStatusException(
                HttpStatus.UNAUTHORIZED, "Invalid email or password"
            );
        }

        String token = jwtService.generateToken(user);

        return new LoginResponse(

            token,
            "Bearer",
            user.getUserId(),
            user.getFullName(),
            user.getEmail(),
            user.getRole().name()
        );
    }
}