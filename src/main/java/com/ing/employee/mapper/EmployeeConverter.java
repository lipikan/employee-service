package com.ing.employee.mapper;

import org.springframework.stereotype.Component;

import com.ing.employee.dto.AddressDto;
import com.ing.employee.dto.EmployeeDto;
import com.ing.employee.models.Employee;

@Component
public class EmployeeConverter {
	
	public Employee mapEmployeeDto(Employee employee, EmployeeDto employeeDto) {
		
		if(employeeDto.getFirstName() != null) {
			employee.setFirstName(employeeDto.getFirstName());
		}
		if (employeeDto.getLastName() != null) {
			employee.setLastName(employeeDto.getLastName());
		}
		if(employeeDto.getGender() != null) {
			employee.setGender(employeeDto.getGender());
		}
		if(employeeDto.getTitle() != null) {
			employee.setTitle(employeeDto.getTitle());
		}
		if(employeeDto.getAddress() != null) {
			if(employee.getAddress().getCity() != null) {
				employee.getAddress().setCity(employeeDto.getAddress().getCity());
			}
			if(employee.getAddress().getState() != null) {
				employee.getAddress().setState(employeeDto.getAddress().getState());
			}
			if(employee.getAddress().getStreet() != null) {
				employee.getAddress().setStreet(employeeDto.getAddress().getStreet());
			}
			if(employee.getAddress().getPostCode() != 0) {
				employee.getAddress().setPostCode(employeeDto.getAddress().getPostCode());
			}
		}
		return employee;
	}
	
	public EmployeeDto mapToDto(Employee employee) {
		AddressDto addressdto = AddressDto.builder()
								.street(employee.getAddress().getStreet())
								.city(employee.getAddress().getCity())
								.state(employee.getAddress().getState())
								.postCode(employee.getAddress().getPostCode())
								.build();
		
		return EmployeeDto.builder()
				.empId(employee.getEmpId())
				.firstName(employee.getFirstName())
				.lastName(employee.getLastName())
				.gender(employee.getGender())
				.title(employee.getTitle())
				.address(addressdto)
				.build();
	}

}
