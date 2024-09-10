package com.bootcampPragma.EmazonTest01.adapters.driven.jpa.mysql.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "brands")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class BrandEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = true, length = 50)
    @NotBlank(message = "Field 'name' cannot be blank")
    @Size(max = 50, message = "Field 'name' cannot exceed 50 characters")
    private String name;

    @Column(name = "description", nullable = false, length = 90)
    @NotBlank(message = "Field 'description' cannot be blank")
    @Size(max = 120, message = "Field 'description' cannot exceed 90 characters")
    private String description;
}
