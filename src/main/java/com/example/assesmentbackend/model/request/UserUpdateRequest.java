package com.example.assesmentbackend.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateRequest {
    @JsonProperty("account_expired")
    private Boolean accountExpired;
    @JsonProperty("account_locked")
    private Boolean accountLocked;
    @JsonProperty("credential_is_expired")
    private Boolean credentialIsExpired;
    @JsonProperty("username")
    private String username;
    @JsonProperty("password")
    private String password;
    @JsonProperty("fullname")
    private String fullname;
    @JsonProperty("email")
    private String email;
    @JsonProperty("is_active")
    private Boolean isActive;
}
