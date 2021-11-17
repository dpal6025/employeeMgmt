package com.employee.management.service;

import java.util.List;

import javax.validation.Valid;

import com.employee.management.dto.EmployeeDto;
import com.employee.management.model.Contract_Employee;
import com.employee.management.model.Employee;
import com.employee.management.model.Regular_Employee;


public interface EmployeeService {
	
	
	// fetching all employees
	public List<Employee> getAllEmployees();
	
	// inserting employee
	public void addEmployee(EmployeeDto e) ;
	
	// updating employee by id
	public void updateRegularEmployee(Employee emp,int id);
	
	public void updateContractEmployee(Employee emp,int id);
	// deleting employee by id
	public void deleteEmployeeByID(int id);

	public Employee findEmployeeById(int id);

	public Employee addEmployeeDetails(Employee employee);

	
}
