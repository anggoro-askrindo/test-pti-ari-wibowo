package com.example.assesmentbackend.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "m_user")
public class User {
    @Id
    @GeneratedValue
    @Column(name = "id_user")
    private UUID id_user;
    @Column(name = "account_expired")
    private boolean account_expired;
    @Column(name = "account_locked")
    private boolean account_locked;
    @Column(name = "credential_is_expired")
    private boolean credential_is_expired;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "fullname")
    private String fullname;
    @Column(name = "email")
    private String email;
    @Column(name = "login_attempt")
    private int login_attempt;
    @Column(name = "is_active")
    private boolean is_active;
    @Column(name = "version")
    private int version;
    @Column(name = "created_by")
    private String created_by;
    @Column(name = "created_date")
    private Timestamp created_date;
    @Column(name = "modified_by")
    private String modified_by;
    @Column(name = "modified_date")
    private Timestamp modified_date;
}
