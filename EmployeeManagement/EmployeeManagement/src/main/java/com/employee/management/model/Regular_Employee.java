package com.employee.management.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;  

@Entity  
@Setter
@Getter
@DiscriminatorValue("regularemployee")  
public class Regular_Employee extends Employee{  
      
@Column(name="salary")    
private float salary;  
 @Column(name="bonus")     
 private int bonus;

}  
