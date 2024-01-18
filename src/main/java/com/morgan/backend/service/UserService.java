package com.morgan.backend.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.morgan.backend.dto.UserDTO;
import com.morgan.backend.model.User;
import com.morgan.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public void create(UserDTO user) throws Exception {
        User userExist = userRepository.findByEmail(user.getEmail()).orElse(null);
        if (userExist != null)
            throw new Exception("You have already an account with this email.");
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(dtoToUser(user));
    }

    public List<UserDTO> getAll() {
        return userRepository.findAll().stream().map(this::userToDto).toList();
    }

    public UserDTO getById(Long id) throws Exception {
        User userFounded = userRepository.findById(id).orElse(null);
        if (userFounded == null)
            throw new Exception("The user with id " + id + " not exist");
        return userToDto(userFounded);
    }

    private UserDTO userToDto(User user) {
        return new ObjectMapper().convertValue(user, UserDTO.class);
    }

    private User dtoToUser(UserDTO user) {
        return new ObjectMapper().convertValue(user, User.class);
    }
}
