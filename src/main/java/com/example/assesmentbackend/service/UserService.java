package com.example.assesmentbackend.service;

import com.example.assesmentbackend.exception.DataNotFoundException;
import com.example.assesmentbackend.model.entity.User;
import com.example.assesmentbackend.model.entity.UserRole;
import com.example.assesmentbackend.model.request.UserRequest;
import com.example.assesmentbackend.model.request.UserUpdateRequest;
import com.example.assesmentbackend.repo.UserRepository;
import com.example.assesmentbackend.repo.UserRoleRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;

@Service(value = "userService")
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final BCryptPasswordEncoder bcryptEncoder;

    public UserService(UserRepository userRepository, UserRoleRepository userRoleRepository, BCryptPasswordEncoder bcryptEncoder) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.bcryptEncoder = bcryptEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(s);
        if(user == null){
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        Set<SimpleGrantedAuthority> authorities = getAuthority(user);
        user.setLogin_attempt(user.getLogin_attempt()+1);
        userRepository.save(user);
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }

    private Set<SimpleGrantedAuthority> getAuthority(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        userRoleRepository.findAllById(Collections.singleton(user.getId_user())).forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        });
        return authorities;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public void deleteOne(String username) {
        User user = userRepository.findByUsername(username);
        if (user==null) throw new DataNotFoundException("User tidak ditemukan");
        userRoleRepository.deleteById(user.getId_user());
        userRepository.delete(user);
    }

    public User findOne(String username) {
        return userRepository.findByUsername(username);
    }

    public User updateUser(UserUpdateRequest userUpdateRequest) {
        User user = findOne(userUpdateRequest.getUsername());
        if (userUpdateRequest.getAccountExpired() != null) user.set_active(userUpdateRequest.getAccountExpired());
        if (userUpdateRequest.getAccountLocked() != null) user.set_active(userUpdateRequest.getAccountLocked());
        if (userUpdateRequest.getCredentialIsExpired() != null) user.set_active(userUpdateRequest.getCredentialIsExpired());
        if (userUpdateRequest.getUsername() != null) user.setUsername(userUpdateRequest.getUsername());
        if (userUpdateRequest.getPassword() != null) user.setPassword(bcryptEncoder.encode(userUpdateRequest.getPassword()));
        if (userUpdateRequest.getFullname() != null) user.setFullname(userUpdateRequest.getFullname());
        if (userUpdateRequest.getEmail() != null) user.setFullname(userUpdateRequest.getEmail());
        if (userUpdateRequest.getIsActive() != null) user.set_active(userUpdateRequest.getIsActive());
        user.setModified_by("ADMIN");
        user.setModified_date(new Timestamp(new Date().getTime()));
        return userRepository.save(user);
    }

    public User save(UserRequest userRequest) {
        User user = new User();
        user.setUsername(userRequest.getUsername());
        user.setFullname(userRequest.getFullName());
        user.setPassword(bcryptEncoder.encode(userRequest.getPassword()));
        user.setAccount_expired(false);
        user.setAccount_locked(false);
        user.setCredential_is_expired(false);
        user.setEmail(userRequest.getEmail());
        user.setLogin_attempt(0);
        user.set_active(true);
        user.setCreated_by("ADMIN");
        user.setCreated_date(new Timestamp(new Date().getTime()));
        User userSaved = userRepository.save(user);

        UserRole userRole = new UserRole();
        userRole.setIdUser(userSaved.getId_user());
        userRole.setRoleName(userRequest.getRoleName());
        userRoleRepository.save(userRole);
        return userSaved;
    }
}
