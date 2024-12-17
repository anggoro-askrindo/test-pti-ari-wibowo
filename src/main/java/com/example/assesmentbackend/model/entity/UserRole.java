package com.example.assesmentbackend.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "r_user_roles")
public class UserRole {
    @Id
    @Column(name = "id_user")
    private UUID idUser;
    @Column(name = "role_name")
    private String roleName;
}
