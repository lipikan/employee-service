package com.ing.employee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.ing.employee.dto.EmployeeDto;
import com.ing.employee.exception.ResourceNotFoundException;
import com.ing.employee.mapper.EmployeeConverter;
import com.ing.employee.models.Employee;
import com.ing.employee.repository.EmployeeRepository;

@Component
public class EmployeeMgmtService {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private EmployeeConverter employeeConverter;

	public ResponseEntity<EmployeeDto> updateEmployee(String employeeId, EmployeeDto employee) throws ResourceNotFoundException {
		
		Employee existingEmployee = employeeRepository
										.findById(employeeId)
										.orElseThrow(() -> new ResourceNotFoundException("Employee not found :: " + employeeId));
		
		existingEmployee = employeeConverter.mapEmployeeDto(existingEmployee, employee);
		
		employeeRepository.save(existingEmployee);
		
		return ResponseEntity.ok(employeeConverter.mapToDto(existingEmployee));
		
	}
	
	public ResponseEntity<EmployeeDto> getEmployeeDetails(String employeeId) throws ResourceNotFoundException {
		Employee employeeDetails = employeeRepository
				.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found :: " + employeeId));
		
		return ResponseEntity.ok().body(employeeConverter.mapToDto(employeeDetails));
	}
	
}
