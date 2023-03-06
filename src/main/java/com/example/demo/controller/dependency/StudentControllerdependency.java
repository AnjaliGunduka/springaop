package com.example.demo.controller.dependency;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Student;
import com.example.demo.service.StudentService;

@RestController
@RequestMapping("/aop")
public class StudentControllerdependency {
	
	
	StudentService studentService;
	@Autowired
	public StudentControllerdependency(StudentService studentService) {
		super();
		this.studentService = studentService;
	}
	
	public ResponseEntity<List<Student>> getStudents() {
		List<Student> student = studentService.getAllStudents();
		return ResponseEntity.status(HttpStatus.OK).body(student);
	}

}
