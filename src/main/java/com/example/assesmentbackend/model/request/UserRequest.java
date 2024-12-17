package com.example.assesmentbackend.model.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

    private String fullName;
    private String username;
    private String password;
    private String roleName;
    private String email;
    
}