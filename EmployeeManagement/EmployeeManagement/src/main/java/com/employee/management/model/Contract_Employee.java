package com.employee.management.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;  

@Entity  
@Setter
@Getter
@DiscriminatorValue("contractemployee")  
public class Contract_Employee extends Employee{  
    
    @Column(name="pay_per_hour")  
   private float pay_per_hour;  
    
   @Column(name="contract_duration")  
   private String contract_duration;

}  
