package com.morgan.backend.configuration;

import com.morgan.backend.dto.UserDTO;
import com.morgan.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;
    @PostMapping("")
    public void createUser(@RequestBody UserDTO user) throws Exception {
        userService.create(user);
    }
}
