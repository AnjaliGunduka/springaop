package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.apache.tomcat.util.bcel.classfile.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.constants.Constants;
import com.example.demo.entity.College;
import com.example.demo.entity.Student;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.StudentNotFoundException;
import com.example.demo.mapper.StudentMapper;
import com.example.demo.repository.CollegeRepository;
import com.example.demo.repository.StudentRepository;
import com.example.demo.request.StudentRequest;
import com.example.demo.response.StudentResponse;

import lombok.CustomLog;

@Service
public class StudentServiceImp implements StudentService {

	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	private CollegeRepository collegeRepository;

	@Override
	public StudentResponse createStudent(StudentRequest studentRequest)   {
		// TODO Auto-generated method stub
		Optional<Student> findByEmailId = studentRepository.findByEmailId(studentRequest.getEmailId());
		if(findByEmailId.isPresent())
		{
			throw new BadRequestException(Constants.STUDENT_ALREADY_PRESENT);
		}
		Student student = StudentMapper.getStudent(studentRequest, new Student());
		studentRepository.save(student);
		College college = StudentMapper.getCollege(studentRequest, new College(), student);
		collegeRepository.save(college);
		return StudentMapper.getStudentResponse(student);
	}
	@Override
	//@CustomAnnotation
	public Student getStudent(Long id) {
		// TODO Auto-generated method stub
		Student student = studentRepository.findById(id).orElse(null);
		return student;
	}
	@Override
	public List<Student> getAllStudents() {
		// TODO Auto-generated method stub
		return studentRepository.findAll();
	}
}
