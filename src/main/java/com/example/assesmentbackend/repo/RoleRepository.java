package com.example.assesmentbackend.repo;

import com.example.assesmentbackend.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, String>  {
}
