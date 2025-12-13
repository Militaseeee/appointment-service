package com.vettrack_CAV.appointment_service.infrastructure.adapters.out.jpa.entity;

import com.vettrack_CAV.appointment_service.domain.model.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(name = "identification")
    private String identification;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

}