package com.morgan.backend.dto;

import com.morgan.backend.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDTO {
    private Long id;
    private String userName;
    private String password;
    private String email;
    private Role role;

    public UserDTO setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public UserDTO setPassword(String password) {
        this.password = password;
        return this;
    }

    public UserDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public UserDTO setRole(Role role) {
        this.role = role;
        return this;
    }
}
