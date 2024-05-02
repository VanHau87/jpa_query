package com.hnguyen387.jpa_query.criterialAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hnguyen387.jpa_query.criterialAPI.models.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>, EmployeeDao{
	
}
