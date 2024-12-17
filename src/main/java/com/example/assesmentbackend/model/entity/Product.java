package com.example.assesmentbackend.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "product")
public class Product {

    @Id
    @NotNull
    @Column(name = "product_code", nullable = false)
    private String productCode;

    @Size(max = 150)
    @Column(name = "product_name", length = 150)
    private String productName;

}