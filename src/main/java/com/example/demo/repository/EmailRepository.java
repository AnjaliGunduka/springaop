package com.example.demo.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Emails;

public interface EmailRepository extends  JpaRepository<Emails, Long>{

	Collection<Emails> findById(Integer id);

}
