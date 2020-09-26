package com.telia.employee.controller;

import com.telia.employee.dto.EmployeeDto;
import com.telia.employee.dto.SaveEmployeeRequest;

public class IntegrationTestHelper {

  public static SaveEmployeeRequest getSaveEmployeeRequest(Long employeId, String firstName, String lastName) {
    SaveEmployeeRequest request = new SaveEmployeeRequest();
    EmployeeDto employeeDto = new EmployeeDto();
    employeeDto.setEmployeeId(employeId);
    employeeDto.setFirstName(firstName);
    employeeDto.setLastName(lastName);
    request.setEmployee(employeeDto);
    return request;
  }
}
