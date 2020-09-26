package com.telia.employee.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetEmployeeResponse extends GenericResponse {
      private EmployeeDto employee;
}
