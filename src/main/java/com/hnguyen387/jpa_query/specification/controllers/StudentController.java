package com.hnguyen387.jpa_query.specification.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hnguyen387.jpa_query.specification.domain.Student;
import com.hnguyen387.jpa_query.specification.dtos.RequestDto;
import com.hnguyen387.jpa_query.specification.services.StudentService;


@RestController
@RequestMapping("api/jpa/students")
public class StudentController {
	@Autowired
	private StudentService service;
	
	@GetMapping("{id}")
	public ResponseEntity<Student> getById(@PathVariable("id") Long id) {
		Student student = service.findById(id);
		return new ResponseEntity<Student>(student, HttpStatus.OK);
	}
	@GetMapping("city/{city}")
	 public ResponseEntity<List<Student>> getByAddressCity(@PathVariable("city") String city) {
		List<Student> students = service.findByCity(city);
		return new ResponseEntity<List<Student>>(students, HttpStatus.OK);
	}
	@GetMapping("name/{name}")
	public ResponseEntity<List<Student>> getByName(@PathVariable("name") String name) {
		List<Student> students = service.findByName(name);
		return new ResponseEntity<List<Student>>(students, HttpStatus.OK);
	}
	@PostMapping("specs")
	public ResponseEntity<List<Student>> findStudents(@RequestBody RequestDto request) {
		List<Student> student = service.findStudents(request);
		return new ResponseEntity<List<Student>>(student, HttpStatus.OK);
	}
	@PostMapping("specs/pagination")
	public ResponseEntity<Page<Student>> findStudentsInPage(@RequestBody RequestDto request) {
		Page<Student> student = service.findStudentsInPage(request);
		return new ResponseEntity<Page<Student>>(student, HttpStatus.OK);
	}
}
