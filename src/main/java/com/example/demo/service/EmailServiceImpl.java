package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.Email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Emails;
import com.example.demo.repository.EmailRepository;

@Service
public class EmailServiceImpl implements EmailService {
	@Autowired
	private JavaMailSender mailSender;
	List<Emails> emailList;
	@Autowired
	EmailRepository emailrepo;

	public void EmailService(JavaMailSender mailSender) {
		this.mailSender = mailSender;
		emailList = new ArrayList();// to create new object from email list class
	}

	@Override
	public Emails sendSimpleEmail(Emails email) {
		// TODO Auto-generated method stub
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(email.getEmailReceiver());
		message.setText(email.getBody());
		message.setSubject(email.getSubject());
		mailSender.send(message);
		System.out.println("Mail Send");
		emailList.add(email);
		Emails save = emailrepo.save(email);
		return save;

	}

	public List<Emails> getEmailList() {
		return emailrepo.findAll();
	}

	public List<Emails> getEmailById(Integer id) {
		return emailrepo.findById(id).stream()
				.filter(email -> email.getId().equals(id)).collect(Collectors.toList());// to																													// id
	}

}
