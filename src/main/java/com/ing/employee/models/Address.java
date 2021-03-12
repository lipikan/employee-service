package com.ing.employee.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "address")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Address {

	 @Id
	 @Column(name = "emp_id")
	 private String empId;
	 
	 @Column(name = "street")
	 private String street;
	 
	 @Column(name = "city")
	 private String city;
	 
	 @Column(name = "state")
	 private String state;
	  
	 @Column(name = "post_code")
	 private int postCode;
	 
	 @OneToOne
	 @MapsId
	 @JoinColumn(name = "emp_id")
	 private Employee employee;
}
