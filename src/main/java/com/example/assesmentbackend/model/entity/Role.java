package com.example.assesmentbackend.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "m_role")
public class Role {
    @Id
    @Column(name = "role_name")
    private String roleName;
    @Column(name = "keterangan")
    private String keterangan;
    @Column(name = "is_active")
    private Boolean isActive;
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
