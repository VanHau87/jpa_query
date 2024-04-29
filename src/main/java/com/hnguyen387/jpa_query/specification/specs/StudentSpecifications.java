package com.hnguyen387.jpa_query.specification.specs;

import org.springframework.data.jpa.domain.Specification;

import com.hnguyen387.jpa_query.specification.domain.Student;

public class StudentSpecifications {
	
	public static Specification<Student> hasName(String name) {
		return (root, query, builder) -> 
			//builder.equal(root.get("name"), name);
			builder.like(root.get("name"), "%"+ name + "%");
	}
	public static Specification<Student> hasAddress(String address) {
		return (root, query, builder) -> builder.equal(root.get("address").get("city"), address);
	}
	public static Specification<Student> hasId(Long id) {
		return (root, query, builder) -> builder.equal(root.get("id"), id);
	}
}
