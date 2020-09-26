package com.telia.employee.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {
  private Long employeeId;

  private String firstName;

  private String lastName;
}
