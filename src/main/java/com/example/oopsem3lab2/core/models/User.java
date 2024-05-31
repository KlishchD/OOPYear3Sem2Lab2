package com.example.oopsem3lab2.core.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "user_entity")
@Getter @Setter
public class User {
    @Id
    @Column(name = "username")
    private String username;

    @Column(name = "first_name")
    private String firstname;

    @Column(name = "last_name")
    private String lastname;

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", name='" + firstname + lastname + '\'' +
                '}';
    }
}