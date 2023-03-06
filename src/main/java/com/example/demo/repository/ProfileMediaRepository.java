package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.ProfileMedia;

@Repository
public interface ProfileMediaRepository extends  JpaRepository<ProfileMedia, Long>{

}
