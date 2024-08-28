package com.bootcampPragma.EmazonTest01.adapters.driven.jpa.mysql.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "categories")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = true, length = 50)
    @NotBlank(message = "Field 'name' cannot be blank")
    @Size(max = 50, message = "Field 'name' cannot exceed 50 characters")
    private String name;

    @Column(name = "description", nullable = false, length = 90)
    @NotBlank(message = "Field 'description' cannot be blank")
    @Size(max = 90, message = "Field 'description' cannot exceed 90 characters")
    private String description;
}