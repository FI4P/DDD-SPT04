package com.springapp.redeAncora.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Table(name = "product")
@Entity
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String brand;

    @Column(name = "vehicle_code", nullable = false)
    private String vehicleCode;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal preco;

}
