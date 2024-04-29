package com.hnguyen387.jpa_query.specification.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.hnguyen387.jpa_query.specification.domain.Student;
import com.hnguyen387.jpa_query.specification.dtos.RequestDto;

public interface StudentService {
	Student findById(Long studentId);
	List<Student> findByName(String name);
	List<Student> findByCity(String city);
	List<Student> findStudents(RequestDto dto);
	Page<Student> findStudentsInPage(RequestDto dto);
}
