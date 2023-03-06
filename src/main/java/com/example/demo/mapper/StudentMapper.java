package com.example.demo.mapper;

import java.time.LocalDateTime;

import org.springframework.beans.BeanUtils;

import com.example.demo.constants.Constants;
import com.example.demo.entity.College;
import com.example.demo.entity.Student;
import com.example.demo.request.StudentRequest;
import com.example.demo.response.StudentResponse;

public class StudentMapper {

	public static Student getStudent(StudentRequest studentRequest, Student student) {
		BeanUtils.copyProperties(studentRequest, student);
		student.setFirstName(studentRequest.getFirstName());
		student.setLastName(studentRequest.getLastName());
		student.setPhoneNo(studentRequest.getPhoneNo());
		student.setEmailId(studentRequest.getEmailId());
		student.setCreatedAt(LocalDateTime.now());
		student.setUpdatedAt(LocalDateTime.now());
		return student;
	}

	public static College getCollege(StudentRequest studentRequest, College college, Student student) {

		BeanUtils.copyProperties(studentRequest, student);
		college.setClgAddress(studentRequest.getClgAddress());
		college.setClgname(studentRequest.getClgname());
		college.setStudent(student);
		return college;
	}

	public static StudentResponse getStudentResponse(Student student) {
		StudentResponse studentResponse = new StudentResponse();
		studentResponse.setFirstName(student.getFirstName());
		studentResponse.setMessage(Constants.STUDENT_ADDED_SUCCESSFULLY);
		studentResponse.setStatus(Constants.SUCCESS);
		return studentResponse;

	}

}
