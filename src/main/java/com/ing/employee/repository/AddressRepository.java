package com.ing.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.ing.employee.models.Address;

@Transactional(readOnly = true)
public interface AddressRepository extends JpaRepository<Address, Long>{

}
