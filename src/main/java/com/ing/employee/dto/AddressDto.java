package com.ing.employee.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {
	 
	private String street;
	
	private String city;
	 
	private String state;
	  
	private int postCode;
}
