package com.hnguyen387.jpa_query.specification.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hnguyen387.jpa_query.specification.domain.Address;

public interface AddressRepository extends JpaRepository<Address, Long>{

}
