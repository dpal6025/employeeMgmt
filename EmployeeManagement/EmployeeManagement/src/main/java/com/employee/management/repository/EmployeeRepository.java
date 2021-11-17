package com.employee.management.repository;



import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.employee.management.model.Employee;


// interface extending crud repository
@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Integer>{

	@Modifying
	@Transactional
	@Query(value="update Employee  set first_name=:#{#emp.firstName},last_name=:#{#emp.lastName},bonus=:#{#emp.bonus},salary=:#{#emp.salary} where employee_id=:id",nativeQuery=true)
	void updateRegular(@Param("emp") Employee emp,@Param("id") int id);

	@Modifying
	@Transactional
	@Query(value="update Employee  set first_name=:#{#emp.firstName},last_name=:#{#emp.lastName},pay_per_hour=:#{#emp.pay_per_hour},contract_duration=:#{#emp.contract_duration} where employee_id=:id",nativeQuery=true)
	void updateContract(@Param("emp") Employee emp,@Param("id") int id);

	
}
