package com.example.demo.service;

import java.util.List;

import javax.validation.constraints.Email;

import com.example.demo.entity.Emails;

public interface EmailService {
	Emails sendSimpleEmail(Emails email);

	List<Emails> getEmailList();

	List<Emails> getEmailById(Integer emailId);

	
}
