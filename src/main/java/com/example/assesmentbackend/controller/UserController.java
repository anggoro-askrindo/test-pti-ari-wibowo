package com.example.assesmentbackend.controller;

import com.example.assesmentbackend.config.TokenProvider;
import com.example.assesmentbackend.model.AuthToken;
import com.example.assesmentbackend.model.entity.User;
import com.example.assesmentbackend.model.request.LoginUser;
import com.example.assesmentbackend.model.request.UserRequest;
import com.example.assesmentbackend.model.request.UserUpdateRequest;
import com.example.assesmentbackend.model.response.ApiResponse;
import com.example.assesmentbackend.repo.UserRepository;
import com.example.assesmentbackend.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final TokenProvider jwtTokenUtil;

    public UserController(UserService userService, AuthenticationManager authenticationManager, TokenProvider jwtTokenUtil) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @PostMapping(value = "/login")
    public ApiResponse<Object> login(@Valid @RequestBody LoginUser loginUser) throws AuthenticationException {

        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUser.getUsername(),
                        loginUser.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = jwtTokenUtil.generateToken(authentication);
        return ApiResponse.ok(new AuthToken(token), "User found");
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value="/create")
    public ApiResponse<Object> saveUser(@RequestBody UserRequest user){
        return ApiResponse.ok(userService.save(user), "User Created");
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping()
    public ApiResponse<Object> updateUser(@RequestBody UserUpdateRequest userUpdateRequest) {
        User user = userService.updateUser(userUpdateRequest);
        return ApiResponse.ok(user, "User Updated");
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping()
    public ApiResponse<Object> getUser() {
        return ApiResponse.ok(userService.findAll(), "User Found");
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping()
    public ApiResponse<Object> deleteUser(@RequestParam("username") String username) {
        userService.deleteOne(username);
        return ApiResponse.ok(null, "User Deleted");
    }
}
