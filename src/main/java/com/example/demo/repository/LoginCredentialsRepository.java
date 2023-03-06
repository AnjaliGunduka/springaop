package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.LoginCredentials;
import com.example.demo.entity.User;


public interface LoginCredentialsRepository extends JpaRepository<LoginCredentials, Long> {

	Optional<LoginCredentials> findByUserId(User users);

	

}
