package com.employee.management.dto;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.employee.management.model.Contract_Employee;
import com.employee.management.model.Department;
import com.employee.management.model.Employee;
import com.employee.management.model.Regular_Employee;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class EmployeeDto {
	private String type;
	
	private String firstName;
	
	private String lastName;
	
	private int department_id;
    private int pay_per_hour;
    private int contract_duration;
  
private float salary;  
     
private int bonus;
}
