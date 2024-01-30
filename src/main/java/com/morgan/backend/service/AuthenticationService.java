package com.morgan.backend.service;

import com.morgan.backend.configuration.JwtService;
import com.morgan.backend.dto.LoginDTO;
import com.morgan.backend.dto.RegisterDTO;
import com.morgan.backend.enums.Role;
import com.morgan.backend.model.User;
import com.morgan.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;
    public String login(LoginDTO loginDto) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));
        var user = userRepository.findByEmail(loginDto.getEmail()).orElseThrow();
        return jwtService.generateToken(user);
    }

    public String register(RegisterDTO registerDTO) {
        var user = User.builder()
            .userName(registerDTO.getUserName())
            .password(passwordEncoder.encode(registerDTO.getPassword()))
            .email(registerDTO.getEmail())
            .role(Role.USER)
            .build();
        userRepository.save(user);
        return jwtService.generateToken(user);
    }
}
