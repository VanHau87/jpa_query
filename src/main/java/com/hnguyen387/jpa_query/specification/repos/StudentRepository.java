package com.hnguyen387.jpa_query.specification.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.hnguyen387.jpa_query.specification.domain.Student;


public interface StudentRepository extends JpaRepository<Student, Long>, JpaSpecificationExecutor<Student>{
	List<Student> findByName(String name);
	List<Student> findByAddressCity(String address);
}
