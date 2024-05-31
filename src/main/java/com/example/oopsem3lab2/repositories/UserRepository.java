package com.example.oopsem3lab2.repositories;

import com.example.oopsem3lab2.core.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {

}