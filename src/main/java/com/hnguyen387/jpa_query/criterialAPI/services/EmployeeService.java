package com.hnguyen387.jpa_query.criterialAPI.services;

import java.util.List;

import com.hnguyen387.jpa_query.criterialAPI.models.Employee;
import com.hnguyen387.jpa_query.criterialAPI.utils.RequestUpdateDto;
import com.hnguyen387.jpa_query.criterialAPI.utils.SearchDto;

public interface EmployeeService {
	Employee createEmployee(Employee employee);
	List<Employee> findBy(SearchDto dto);
	List<Employee> findAll();
	int updateEmployee(RequestUpdateDto dto);
	int deleteEmployees(SearchDto dto);
}
