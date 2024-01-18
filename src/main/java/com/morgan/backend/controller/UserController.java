package com.morgan.backend.controller;

import com.morgan.backend.dto.UserDTO;
import com.morgan.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;
    @PostMapping("")
    public void createUser(@RequestBody UserDTO user) throws Exception {
        userService.create(user);
    }

    @GetMapping("")
    public List<UserDTO> getAllUsers(){
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable("id") Long id) throws Exception {
        return userService.getById(id);
    }
}
