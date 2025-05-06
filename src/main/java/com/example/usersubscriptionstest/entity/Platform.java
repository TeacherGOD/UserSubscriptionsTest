package com.example.usersubscriptionstest.entity;

import lombok.AllArgsConstructor;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "platforms")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Platform {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String url;

    @Column(nullable = false)
    private Double price;
}
