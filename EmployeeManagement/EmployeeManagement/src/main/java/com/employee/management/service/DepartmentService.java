package com.employee.management.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.management.model.Department;
import com.employee.management.repository.DepartmentRepository;


public interface DepartmentService {

	// fetching all department
	public List<Department> getAllDepartments();
	
	
	// inserting department
	public void addDepartment(Department d) ;
	
	// updating department by id
	public void updateDepartment(Department d, int id);
	
	
	// deleting department by id
	public void deleteDepartmentByID(int id);


	public Department findDepartmentById(int id);

	
}
