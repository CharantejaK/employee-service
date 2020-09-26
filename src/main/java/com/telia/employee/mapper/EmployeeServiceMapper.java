package com.telia.employee.mapper;

import com.telia.employee.dto.EmployeeDto;
import com.telia.employee.entity.Employee;
import org.springframework.stereotype.Component;

@Component
public class EmployeeServiceMapper {

  public Employee map(EmployeeDto employeeDto) {
    Employee employee = new Employee();
    employee.setEmployeeId(employeeDto.getEmployeeId());
    employee.setFirstName(employeeDto.getFirstName());
    employee.setLastName(employeeDto.getLastName());
    return employee;
  }

  public EmployeeDto map(Employee employee) {
    EmployeeDto employeeDto = new EmployeeDto();
    employeeDto.setEmployeeId(employee.getEmployeeId());
    employeeDto.setFirstName(employee.getFirstName());
    employeeDto.setLastName(employee.getLastName());
    return employeeDto;
  }
}
