package com.phamthainguyen.website.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.phamthainguyen.website.model.entity.User;
import com.phamthainguyen.website.model.entity.User.Role;
import com.phamthainguyen.website.model.request.LoginRequest;
import com.phamthainguyen.website.model.request.RegisterRequest;
import com.phamthainguyen.website.model.response.AuthResponse;
import com.phamthainguyen.website.responsitory.UserRepository;

@Service
public class AuthService {

    @Autowired
    private UserRepository userResponsitory;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean isEmail(String email) {
        return userResponsitory.findByEmail(email) != null;
    }

    public AuthResponse login(LoginRequest request) {
        System.out.println("password " +request.getPassword());
        try{
        authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        }catch(Exception e){
            return AuthResponse.builder().type("Account").msg("Lỗi đăng nhập").build();
        }
        if (!isEmail(request.getEmail())) {
            return AuthResponse.builder().type("email").msg("Email does not exist").build();
        }
        User user = userResponsitory.findByEmail(request.getEmail());
        String token = jwtService.generateToken(user);
        return AuthResponse.builder().type("login").msg("success").token(token).build();
    }

    public AuthResponse register(RegisterRequest request) {
        if (isEmail(request.getEmail())) {
            return AuthResponse.builder().type("email").msg("Email already exists").build();
        }
        User user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .gender(request.getGender())
                .role(Role.USER)
                .build();
        userResponsitory.save(user);
        String token = jwtService.generateToken(user);
        return AuthResponse.builder().type("login").msg("success").token(token).build();
    }

}
