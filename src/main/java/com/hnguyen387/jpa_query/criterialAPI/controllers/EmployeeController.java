package com.hnguyen387.jpa_query.criterialAPI.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hnguyen387.jpa_query.criterialAPI.models.Employee;
import com.hnguyen387.jpa_query.criterialAPI.services.EmployeeService;
import com.hnguyen387.jpa_query.criterialAPI.utils.RequestUpdateDto;
import com.hnguyen387.jpa_query.criterialAPI.utils.SearchDto;

@RestController
@RequestMapping("api/jpa/employees")
public class EmployeeController {
	@Autowired
	private EmployeeService service;
	
	@PostMapping
	public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
		Employee savedEmployee = service.createEmployee(employee);
		return new ResponseEntity<Employee>(savedEmployee, HttpStatus.CREATED);
	}
	@PostMapping("/search")
	public ResponseEntity<List<Employee>> getEmployees(@RequestBody SearchDto dto) {
		List<Employee> employees = service.findBy(dto);
		return new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
	}
	@PostMapping("/deletes")
	public ResponseEntity<Integer> deleteEmployees(@RequestBody SearchDto dto) {
		Integer deleteCount = service.deleteEmployees(dto);
		return new ResponseEntity<Integer>(deleteCount, HttpStatus.OK);
	}
	@PutMapping
	public ResponseEntity<Integer> updateEmployee(@RequestBody RequestUpdateDto dto) {
		Integer updateCount = service.updateEmployee(dto);
		return new ResponseEntity<Integer>(updateCount, HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<Employee>> getAllEmployees() {
		List<Employee> employees = service.findAll();
		return new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
	}
	
}
