package com.tw.apistackbase.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employees")
public class EmployeeResource {
	List<Employee> employees = new ArrayList<Employee>();


	@GetMapping(path = "/")
	public ResponseEntity<List<Employee>> getInfo() {
		return ResponseEntity.ok(employees);
	}
	
	@RequestMapping(path = "/")
	@ResponseStatus(HttpStatus.CREATED)
	public Employee createEmployee(@RequestBody Employee employee) {
		employees.add(employee);
		return employee;
	}
	
	@PutMapping(path = "/")
	public ResponseEntity<Employee> updatEmployee(@RequestBody Employee employee) {
		for (Employee employee2 : employees) {
			if (employee2.getId().equals(employee.getId())) {
				employee2.setAge(employee.getAge());
				employee2.setName(employee.getName());
				employee2.setGender(employee.getGender());
				return ResponseEntity.ok(employee);
			}
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(path = "/{id}")
	public ResponseEntity<Employee> getSingleEmployee(@PathVariable String id) {
		for (Employee employee : employees) {
			if (employee.getId().equals(id)) {
				return ResponseEntity.ok(employee);
			}
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Employee> deleteSingleEmployee(@PathVariable String id) {
		for (Employee employee : employees) {
			if (employee.getId().equals(id)) {
				employees.remove(employee);
				return ResponseEntity.ok(employee);
			}
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	
}
