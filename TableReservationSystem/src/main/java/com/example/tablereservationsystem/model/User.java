package com.example.tablereservationsystem.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.jspecify.annotations.Nullable;

@Entity
public class User {

    @Setter
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Column(unique = true, nullable = false)
    private String email;

    private String password;

    @Getter
    private String role;

    @Getter
    private String name;

    public void setPassword(@Nullable String encode) {
        this.password = encode;
    }

    public @Nullable String getPassword() {
        return password;
    }

}