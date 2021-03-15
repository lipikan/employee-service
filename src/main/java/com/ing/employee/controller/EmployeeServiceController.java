package com.ing.employee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ing.employee.dto.EmployeeDto;
import com.ing.employee.exception.ResourceNotFoundException;
import com.ing.employee.service.EmployeeMgmtService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@Validated
@RequestMapping("/api/v1")
public class EmployeeServiceController {
	
	@Autowired
	private EmployeeMgmtService employeeService;
	
	@PatchMapping(path = "/employees/{employeeId}")
	public ResponseEntity<?> updateEmployee(@PathVariable String employeeId, @RequestBody EmployeeDto employee)
	      throws JsonProcessingException, ResourceNotFoundException {
	    log.info("Inside EmployeeServiceController :: updateEmployee");
	    ResponseEntity<?> updateEmployeeResponseEntity = null;
	    if (employeeId == null || employee == null) {
	      log.error("EmployeeServiceController.updateEmployee():: employee details or employee id cannot be null",
	          HttpStatus.BAD_REQUEST);
	      return new ResponseEntity<>("employee details or employee id cannot be null", HttpStatus.BAD_REQUEST);
	    }
	    try {
	    	Long empId = Long.parseLong(employeeId);
	    	updateEmployeeResponseEntity = employeeService.updateEmployee(empId,
	    	        employee);
	    	
	    } catch(NumberFormatException ex) {
	    	log.error("EmployeeServiceController.updateEmployee():: employee Id should contain numbers only :: {}",
	  	          HttpStatus.BAD_REQUEST);
	  	      return new ResponseEntity<>("employee Id should contain numbers only", HttpStatus.BAD_REQUEST);
	    }
	    log.info("End EmployeeServiceController :: updateEmployee");
	    return updateEmployeeResponseEntity;
	}
	
	@GetMapping(path = "/employees/{employeeId}")
	public ResponseEntity<?> getEmployeeDetails(@PathVariable String employeeId) throws JsonProcessingException, ResourceNotFoundException
	{
		log.info("Inside EmployeeServiceController :: getEmployeeDetails");
		ResponseEntity<EmployeeDto> getEmployeeDetailsResponseEntity = null;
	    if (employeeId == null) {
	      log.error("EmployeeServiceController.getEmployeeDetails():: employee Id cannot be null ", HttpStatus.BAD_REQUEST);
	      return new ResponseEntity<>("employee Id cannot be null ", HttpStatus.BAD_REQUEST);
	    }
	    try {
	    	Long empId = Long.parseLong(employeeId);
	    	getEmployeeDetailsResponseEntity = employeeService.getEmployeeDetails(empId);
	    } catch(NumberFormatException ex) {
	    	log.error("EmployeeServiceController.updateEmployee():: employee Id should contain numbers only :: {}",
	  	          HttpStatus.BAD_REQUEST);
	  	      return new ResponseEntity<>("employee Id should contain numbers only ", HttpStatus.BAD_REQUEST);
	    }
	    
	    log.info("End EmployeeServiceController :: getEmployeeDetails");
	    return getEmployeeDetailsResponseEntity;
		
	}
	

}
