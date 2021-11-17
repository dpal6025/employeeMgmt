package com.employee.management.model;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.*;

import lombok.Data;
import lombok.ToString;

@Entity
@Table (name = "employee")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)  
@DiscriminatorColumn(name="type",discriminatorType=DiscriminatorType.STRING)  
@DiscriminatorValue(value="employee")  
@Data
@ToString
public class Employee {

	@Id
	@Column(name="employee_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int employeeID;
	@NotEmpty(message="FirstName is empty")
	@Column(name="first_name")
	private String firstName;
	@NotEmpty(message="LastName is empty")
	@Column(name="last_name")
	private String lastName;
	

	@ManyToOne(fetch = FetchType.EAGER,cascade=CascadeType.REFRESH)
	@JoinColumn(name="department_id",nullable=false)
	private Department department;
	
}
