package com.ing.employee.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.ing.employee.models.Employee;

@Transactional(readOnly = true) 
public interface EmployeeRepository extends JpaRepository<Employee, Long>{

}
