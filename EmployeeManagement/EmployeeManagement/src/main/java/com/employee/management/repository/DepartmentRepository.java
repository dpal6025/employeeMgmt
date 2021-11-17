package com.employee.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.employee.management.model.Department;

@Repository
public interface DepartmentRepository extends CrudRepository<Department, Integer>,JpaRepository<Department, Integer>{





}
