package com.ing.employee.dto;

import com.ing.employee.models.Gender;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {

	private String empId;
	
	private String firstName;
	
	private String lastName;
	
	private Gender gender;
	  
	private String title;
	  
	private AddressDto address;
}
