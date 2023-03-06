package com.example.demo.entity;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.example.demo.enums.Status;

@Entity
@Table(name = "authentication_details")
public class AuthenticationDetails extends BaseEntity {

	@Column(name = "token")
	private String token;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	private User userId;

	@Column(name = "now")
	private LocalDateTime now;

	@Column(name = "status")
	private Status status;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public User getUserId() {
		return userId;
	}

	public void setUserId(User userId) {
		this.userId = userId;
	}

	public LocalDateTime getNow() {
		return now;
	}

	public void setNow(LocalDateTime now) {
		this.now = now;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "AuthenticationDetails [token=" + token + ", userId=" + userId + ", now=" + now + ", status=" + status
				+ "]";
	}
	
	
	
	
	

}
