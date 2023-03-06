package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Student;
import com.example.demo.exception.StudentNotFoundException;
import com.example.demo.request.StudentRequest;
import com.example.demo.response.StudentResponse;

@Service
public interface StudentService {

	public StudentResponse createStudent(StudentRequest studentRequest) throws StudentNotFoundException;
  

	public Student getStudent(Long id);
	public List<Student> getAllStudents();

}
