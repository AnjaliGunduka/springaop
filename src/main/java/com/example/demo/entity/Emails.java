package com.example.demo.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Emails extends BaseEntity {

	@Email // annotation to validate the email
	private String emailReceiver;
	@NotNull
	@Size(min = 3, max = 50) // to validate the subject min length 3 and max 50
	private String subject;
	@Size(min = 5)
	private String body;

	public String getEmailReceiver() {
		return emailReceiver;
	}

	public void setEmailReceiver(String emailReceiver) {
		this.emailReceiver = emailReceiver;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

}
