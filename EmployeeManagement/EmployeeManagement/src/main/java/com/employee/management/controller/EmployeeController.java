package com.employee.management.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.employee.management.dto.EmployeeDto;
import com.employee.management.exceptionhandling.DepartmentNotFoundException;
import com.employee.management.exceptionhandling.EmployeeNotFoundException;
import com.employee.management.model.Contract_Employee;
import com.employee.management.model.Employee;
import com.employee.management.model.Regular_Employee;
import com.employee.management.service.EmployeeService;

@RestController
@Validated
public class EmployeeController {

	static final Logger logger = LogManager.getLogger(EmployeeController.class.getName());

	@Autowired
	private EmployeeService employeeService;

	// displaying list of all employees
	@GetMapping("/employees")
	public ResponseEntity<List<Employee>> getAllEmployee() {
		List<Employee> list=employeeService.getAllEmployees();
		return Optional.of(list)
	            .map(employeeList -> new ResponseEntity<>(employeeList, HttpStatus.OK))
	            .orElse(new ResponseEntity<>(HttpStatus.NO_CONTENT));
			
					
	}

	// displaying employee by id
	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployee(@PathVariable int id) throws EmployeeNotFoundException {
		Employee emp = employeeService.findEmployeeById(id);
		
		if (emp == null)
			throw new EmployeeNotFoundException("Employee Id not found");
		else
			return new ResponseEntity<>(emp, HttpStatus.OK);
	}

	// inserting employee
	@PostMapping("/employees/regularemployee")
	public ResponseEntity<Regular_Employee> addEmployees(@Valid @RequestBody Regular_Employee employee) throws DepartmentNotFoundException {
	Employee emp=employeeService.addEmployeeDetails(employee);
		if(emp!=null)
		return new ResponseEntity<Regular_Employee>(employee,HttpStatus.CREATED );
		else
			throw new DepartmentNotFoundException("Department Id not found");
		
	}
	@PostMapping("/employees/contractemployee")
	public ResponseEntity<Contract_Employee> addContractEmployees(@Valid @RequestBody Contract_Employee employee) {
		employeeService.addEmployeeDetails(employee);
		return new ResponseEntity<Contract_Employee>(employee,HttpStatus.CREATED );
	}

	@PostMapping("/employees/")
	public ResponseEntity<EmployeeDto> addEmployees(@Valid @RequestBody EmployeeDto employee) throws DepartmentNotFoundException {
		employeeService.addEmployee(employee);
		
		return new ResponseEntity<EmployeeDto>(employee,HttpStatus.CREATED );
		
		
	}
	
	// updating employee by id
	@PutMapping("/employees/updateRegularEmployee/{id}")
	public ResponseEntity<String> updateRegularEmployee(@RequestBody Regular_Employee emp, @PathVariable int id) throws EmployeeNotFoundException {
		Employee employeeData=employeeService.findEmployeeById(id);
		if(employeeData!=null)
		{      employeeService.updateRegularEmployee(emp,id);
			return new ResponseEntity<>("Updation Success for the employeeId "+id,HttpStatus.OK);
		}
	 else {
		 throw new EmployeeNotFoundException("Employee Id not found");
	}
	}
	@PutMapping("/employees/updateContractEmployee/{id}")
	public ResponseEntity<String> updateContractEmployee(@RequestBody Contract_Employee emp, @PathVariable int id) throws EmployeeNotFoundException {
		Employee employeeData=employeeService.findEmployeeById(id);
		if(employeeData!=null)
		{      employeeService.updateContractEmployee(emp,id);
			return new ResponseEntity<>("Updation Success for the employeeId "+id,HttpStatus.OK);
		}
	 else {
		 throw new EmployeeNotFoundException("Employee Id not found");
	}
	}
	
	// deleting employee by id
	@DeleteMapping("employees/{id}")
	public ResponseEntity<String> deleteEmployeeByID(@PathVariable int id) throws EmployeeNotFoundException {
		
		Employee emp = employeeService.findEmployeeById(id);
		
		if (emp == null)
			throw new EmployeeNotFoundException("Employee Id not found");
		else
			return new ResponseEntity<>("Deletion Success", HttpStatus.OK);
	}
}
