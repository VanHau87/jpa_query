package com.hnguyen387.jpa_query.specification.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.hnguyen387.jpa_query.specification.domain.Student;
import com.hnguyen387.jpa_query.specification.dtos.PageRequestDto;
import com.hnguyen387.jpa_query.specification.dtos.RequestDto;
import com.hnguyen387.jpa_query.specification.globalexception.NotFoundException;
import com.hnguyen387.jpa_query.specification.repos.StudentRepository;
import com.hnguyen387.jpa_query.specification.services.StudentService;
import com.hnguyen387.jpa_query.specification.specs.FilterSpecifications;
import com.hnguyen387.jpa_query.specification.specs.StudentSpecifications;

@Service
public class StudentServiceImpl implements StudentService{
	@Autowired
	private StudentRepository repository;
	@Autowired
	private FilterSpecifications<Student> specifications;
	@Override
	public Student findById(Long studentId) {
		//return repository.findById(studentId).orElseThrow(()-> new NotFoundException("Student not exist!"));
		Specification<Student> specs = Specification.where(StudentSpecifications.hasId(studentId));
		return repository.findOne(specs).orElseThrow(()-> new NotFoundException("Student not exist!"));
	}
	@Override
	public List<Student> findByName(String name) {
		//return repository.findByName(name);
		Specification<Student> specs = Specification.where(StudentSpecifications.hasName(name));
		return repository.findAll(specs);
	}
	@Override
	public List<Student> findByCity(String city) {
		//return repository.findByAddressCity(city);
		Specification<Student> specs = Specification.where(StudentSpecifications.hasAddress(city));
		return repository.findAll(specs);
	}
	@Override
	public List<Student> findStudents(RequestDto dto) {
		//Specification<Student> searchSpecificattion = specifications.searchSpecification(dto.getDtos());
		var specificattion = specifications.searchSpecification(dto.getDtos(), dto.getOperator());
		return repository.findAll(specificattion);
	}
	@Override
	public Page<Student> findStudentsInPage(RequestDto dto) {
		var specificattion = specifications.searchSpecification(dto.getDtos(), dto.getOperator());
		Pageable pageable = new PageRequestDto().getPageable(dto.getPageDto());
		return repository.findAll(specificattion, pageable);
	}
}
