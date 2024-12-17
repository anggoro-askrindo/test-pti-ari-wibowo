package com.example.assesmentbackend.repo;

import com.example.assesmentbackend.model.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRoleRepository extends JpaRepository<UserRole, UUID> {
    Optional<UserRole> findUserRoleByRoleName(String roleName);
}
