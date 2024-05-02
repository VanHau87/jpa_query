package com.hnguyen387.jpa_query.criterialAPI.repository;

import java.util.List;

import com.hnguyen387.jpa_query.criterialAPI.models.Employee;
import com.hnguyen387.jpa_query.criterialAPI.utils.RequestUpdateDto;
import com.hnguyen387.jpa_query.criterialAPI.utils.SearchDto;

public interface EmployeeDao {
	List<Employee> findBy(String property, Object value);
	int updateEmployee(RequestUpdateDto dto);
	int deleteBy(SearchDto dto);
}
