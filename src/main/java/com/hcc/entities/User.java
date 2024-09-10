package com.hcc.entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cohortStartDate")
    private LocalDate cohortStartDate;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    public User() {
    }

    public User(LocalDate cohortStartDate, String username, String password) {
        this.cohortStartDate = cohortStartDate;
        this.username = username;
        this.password = password;
    }
}
