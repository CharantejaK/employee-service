package com.telia.employee.dao;

import com.telia.employee.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
  Employee findByEmployeeId(Long employeeId);
  Employee save(Employee employee);
}
