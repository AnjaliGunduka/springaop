package com.example.demo.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.GenericGenerator;

import com.example.demo.validator.OptionalPattern;

@Entity
@Table(name = "student")
public class Student extends BaseEntity{

	
	// @NotEmpty(message = "first name must not be empty")
	@NotBlank(message = "First_Name is mandatory")
	@Column(name = "first_name")
	private String firstName;
	@NotBlank(message = "Last_Name is mandatory")
	@Column(name = "last_name")
	private String lastName;
	@OptionalPattern(message = "Provide valid Mobile number", regexp = "^\\+[1-9]{1}[0-9]{3,14}$")
	private String phoneNo;

	@Column(name = "email_id")
	@OptionalPattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", message = "Invalid Email id")
	@Email
	private String emailId;
	

	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	@Override
	public String toString() {
		return "Student [firstName=" + firstName + ", lastName=" + lastName + ", phoneNo=" + phoneNo + ", emailId="
				+ emailId + "]";
	}

	

}
