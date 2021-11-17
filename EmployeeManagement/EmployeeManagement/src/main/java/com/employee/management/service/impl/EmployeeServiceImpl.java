package com.employee.management.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.management.dto.EmployeeDto;
import com.employee.management.exceptionhandling.DepartmentNotFoundException;
import com.employee.management.model.Contract_Employee;
import com.employee.management.model.Department;
import com.employee.management.model.Employee;
import com.employee.management.model.Regular_Employee;
import com.employee.management.repository.DepartmentRepository;
import com.employee.management.repository.EmployeeRepository;
import com.employee.management.service.EmployeeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

// employee service class
@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	DepartmentRepository departmentRepo;

	// fetching all employees
	public List<Employee> getAllEmployees() {
		List<Employee> emps = (List<Employee>) employeeRepository.findAll();
		return emps;
	}

	// inserting employee
	public void addEmployee(EmployeeDto emp) {
		Department department = new Department();
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode rootNode;
			rootNode = objectMapper.readTree(objectMapper.writeValueAsString(emp));
			System.out.println("RootNode" + rootNode);

			String type = rootNode.get("type").asText();
			if (type.equals("regularemployee")) {
				Regular_Employee regular = new Regular_Employee();
				regular.setFirstName(rootNode.get("firstName").asText());
				regular.setLastName(rootNode.get("lastName").asText());

				int id = rootNode.get("department_id").asInt();
				System.out.println("Id" + id);
				// int dept_id = emp.getDepartment_id();
				List<Department> list = departmentRepo.findAll();
				for (Department dept : list) {
					if (id == dept.getDepartment_ID()) {
						department.setDepartment_ID(id);
					}
				}

				regular.setDepartment(department);
				regular.setBonus(rootNode.get("bonus").asInt());
				regular.setSalary(rootNode.get("salary").asInt());
				employeeRepository.save(regular);
			} else if (type.equals("contractemployee")) {
				Contract_Employee contract = new Contract_Employee();
				contract.setFirstName(rootNode.get("firstName").asText());
				contract.setLastName(rootNode.get("lastName").asText());
				int id = rootNode.get("department_id").asInt();

				List<Department> list = departmentRepo.findAll();
				for (Department dept : list) {
					if (id == dept.getDepartment_ID()) {
						department.setDepartment_ID(id);
					}
				}

				contract.setDepartment(department);
				contract.setPay_per_hour(rootNode.get("pay_per_hour").asInt());
				contract.setContract_duration((rootNode.get("contract_duration").asText()));

				employeeRepository.save(contract);

			}

		} catch (JsonMappingException e1) {

			e1.printStackTrace();
		} catch (JsonProcessingException e1) {

			e1.printStackTrace();
		}
	}

	// updating employee by id
	public void updateRegularEmployee(Employee emp, int id) {

		employeeRepository.updateRegular(emp, id);

	}

	public void updateContractEmployee(Employee emp, int id) {

		employeeRepository.updateContract(emp, id);

	}

	// deleting employee by id
	public void deleteEmployeeByID(int id) {
		employeeRepository.deleteById(id);
	}

	public Employee findEmployeeById(int id) {
		Optional<Employee> emp = employeeRepository.findById(id);
		if (emp.isPresent())
			return emp.get();
		else
			return null;

	}

	public Employee addEmployeeDetails(Employee employee) {

		List<Department> list = departmentRepo.findAll();

		for (Department dept : list) {
			if (dept.getDepartment_ID() == employee.getDepartment().getDepartment_ID()) {
				employeeRepository.save(employee);
				return employee;
			}
		}
		return null;
	}
}
