package com.employee.management.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.management.model.Department;
import com.employee.management.model.Employee;
import com.employee.management.repository.DepartmentRepository;
import com.employee.management.service.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService{

	@Autowired
	private DepartmentRepository departmentRepository;
	

	// fetching all department
	public List<Department> getAllDepartments(){
		List<Department> depts = (List<Department>)departmentRepository.findAll(); 
		return depts;
	}
	
	
	// inserting department
	public void addDepartment(Department d) {
		departmentRepository.save(d);
	}
	
	// updating department by id
	public void updateDepartment(Department d, int id){
		if(id == d.getDepartment_ID()) {
			departmentRepository.save(d);
		}
	}
	public Department findDepartmentById(int id) {
		Optional<Department> dept=departmentRepository.findById(id);
		if(dept.isPresent())
		return dept.get();
		else
			return null;
		
	}
	
	
	
	// deleting department by id
	public void deleteDepartmentByID(int id){
		departmentRepository.deleteById(id);
	}
	
}
