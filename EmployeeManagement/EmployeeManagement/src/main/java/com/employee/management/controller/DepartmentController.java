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

import com.employee.management.exceptionhandling.DepartmentNotFoundException;
import com.employee.management.exceptionhandling.EmployeeNotFoundException;
import com.employee.management.model.Department;
import com.employee.management.model.Employee;
import com.employee.management.service.DepartmentService;

@RestController
@Validated
public class DepartmentController {

	static final Logger logger = LogManager.getLogger(DepartmentController.class.getName());

	@Autowired
	private DepartmentService departmentService;

	// displaying list of all department
	@GetMapping("/departments")
	public ResponseEntity<List<Department>> getAllDepartment() {

		List<Department> list = departmentService.getAllDepartments();
		return Optional.of(list)
	            .map(departmentList -> new ResponseEntity<>(departmentList, HttpStatus.OK))
	            .orElse(new ResponseEntity<>(HttpStatus.NO_CONTENT));
	}

	// displaying department by id
	@GetMapping("/departments/{id}")
	public ResponseEntity<Department> getDepartment(@PathVariable int id) throws DepartmentNotFoundException {

		Department dept = departmentService.findDepartmentById(id);

		if (dept == null)
			throw new DepartmentNotFoundException("Department Id not found");
		else
			return new ResponseEntity<>(dept, HttpStatus.OK);
	}

	// inserting department
	@PostMapping("/departments")
	public ResponseEntity<Department> addDepartment(@Valid @RequestBody Department department) {
		departmentService.addDepartment(department);
		return new ResponseEntity<Department>(department, HttpStatus.CREATED);
	}

	// updating department by id
	@PutMapping("/departments/{id}")
	public ResponseEntity<String> updateDepartment(@RequestBody Department dept, @PathVariable int id) throws DepartmentNotFoundException {
		Department department = departmentService.findDepartmentById(id);
		if (department != null) {
			departmentService.updateDepartment(department, id);
			return new ResponseEntity<>("Updation Success for the departmentId " + id, HttpStatus.OK);
		} else {
			throw new DepartmentNotFoundException("Department Id not found");
		}
	}

	// deleting department by id
	@DeleteMapping("departments/{id}")
	public ResponseEntity<String> deleteDepartmentByID(@RequestBody Department dept, @PathVariable int id) throws DepartmentNotFoundException {
		departmentService.deleteDepartmentByID(id);

		Department department = departmentService.findDepartmentById(id);
		
		if (department == null)
			throw new DepartmentNotFoundException("Department Id not found");
		else
			return new ResponseEntity<>("Deletion Success", HttpStatus.OK);
	}
	}


