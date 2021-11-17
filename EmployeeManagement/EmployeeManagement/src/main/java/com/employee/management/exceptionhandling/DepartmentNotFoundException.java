package com.employee.management.exceptionhandling;

public class DepartmentNotFoundException extends Exception {
	public DepartmentNotFoundException(String message) {
		super(message);
	}
}
