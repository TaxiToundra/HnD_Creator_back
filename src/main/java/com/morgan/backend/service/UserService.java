package com.morgan.backend.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.morgan.backend.dto.UserDTO;
import com.morgan.backend.model.User;
import com.morgan.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public void create(UserDTO user) throws Exception {
        User userExist = userRepository.findByEmail(user.getEmail()).orElse(null);
        if (userExist != null)
            throw new Exception("You have already an account with this email.");
        userRepository.save(new ObjectMapper().convertValue(user, User.class));
    }
}
