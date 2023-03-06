package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Value;

@Document(collection = "college")
public class College {
	
	@Id
	private String id;
	private String clgname;
	private String clgAddress;

	@ManyToOne
	@JoinColumn(name = "student_id")
	private Student student;

	public String getClgname() {
		return clgname;
	}

	

	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public void setClgname(String clgname) {
		this.clgname = clgname;
	}

	public String getClgAddress() {
		return clgAddress;
	}

	public void setClgAddress(String clgAddress) {
		this.clgAddress = clgAddress;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	@Override
	public String toString() {
		return "College [clgname=" + clgname + ", clgAddress=" + clgAddress + ", student=" + student + "]";
	}

	public College(String clgname, String clgAddress, Student student) {
		super();
		this.clgname = clgname;
		this.clgAddress = clgAddress;
		this.student = student;
	}

	public College() {
		super();
	}

}
