package com.example.demo.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.example.demo.enums.MediaTypes;

@Entity
@Table(name = "profile_media")
public class ProfileMedia extends BaseEntity{
	
	@Column(name = "name")
	private String name;

	@Column(name = "file_extension")
	private String fileExtension;

	@Column(name = "size")
	private long size;

	@Column(name = "file")
	@Lob
	private byte[] file;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "student_id")
	private Student student;

	@Column(name = "type")
	private MediaTypes type;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFileExtension() {
		return fileExtension;
	}

	public void setFileExtension(String fileExtension) {
		this.fileExtension = fileExtension;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public byte[] getFile() {
		return file;
	}

	public void setFile(byte[] file) {
		this.file = file;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public MediaTypes getType() {
		return type;
	}

	public void setType(MediaTypes type) {
		this.type = type;
	}

	
	

}
