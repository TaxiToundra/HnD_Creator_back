package com.morgan.backend.service;

import com.morgan.backend.dto.UserDTO;
import com.morgan.backend.model.User;
import com.morgan.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

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
        return new UserDTO()
            .setUserName(user.getUserPseudo())
            .setPassword(user.getPassword())
            .setEmail(user.getEmail())
            .setRole(user.getRole());
    }
}
