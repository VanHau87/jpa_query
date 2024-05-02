package com.hnguyen387.jpa_query.criterialAPI.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hnguyen387.jpa_query.criterialAPI.models.Employee;
import com.hnguyen387.jpa_query.criterialAPI.repository.EmployeeRepository;
import com.hnguyen387.jpa_query.criterialAPI.services.EmployeeService;
import com.hnguyen387.jpa_query.criterialAPI.utils.RequestUpdateDto;
import com.hnguyen387.jpa_query.criterialAPI.utils.SearchDto;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService{
	@Autowired
	private EmployeeRepository repository;
	@Override
	public Employee createEmployee(Employee employee) {
		Employee savedEmployee = repository.save(employee);
		return savedEmployee;
	}
	@Override
	public List<Employee> findBy(SearchDto dto) {
		return repository.findBy(dto.fieldName, dto.value);
	}
	@Override
	public List<Employee> findAll() {
		return repository.findAll();
	}
	@Override
	public int updateEmployee(RequestUpdateDto dto) {
		return repository.updateEmployee(dto);
	}
	@Override
	public int deleteEmployees(SearchDto dto) {
		return repository.deleteBy(dto);
	}

}
