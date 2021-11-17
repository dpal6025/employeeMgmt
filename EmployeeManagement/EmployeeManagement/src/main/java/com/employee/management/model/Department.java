package com.employee.management.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.Data;
import lombok.ToString;

@Entity
@Table(name = "department")
@Data
@ToString
public class Department {

	@Id
	@Column(name = "department_id")
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int department_ID;
		
	private String department_Name;

}
