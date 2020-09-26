package com.telia.employee.controller;

import com.telia.employee.delegate.EmployeeDelegate;
import com.telia.employee.dto.GenericResponse;
import com.telia.employee.dto.GetEmployeeResponse;
import com.telia.employee.dto.SaveEmployeeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

  static final String SAVE_EMPLOYEE_ENDPOINT = "/saveEmployee";
  static final String GET_EMPLOYEE_ENDPOINT = "/getEmployee";
  static final String REQUESTPARAM_EMPLOYEE_ID = "employeeId";

  @Autowired
  EmployeeDelegate employeeDelegate;

  @PostMapping(value = SAVE_EMPLOYEE_ENDPOINT, produces = (MediaType.APPLICATION_JSON_VALUE))
  public @ResponseBody
  ResponseEntity<GenericResponse> saveEmployee(@RequestBody SaveEmployeeRequest employee) {
    GenericResponse response = employeeDelegate.saveEmployee(employee);
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    return new ResponseEntity<>(response, headers, HttpStatus.OK);
  }

  @GetMapping(value = GET_EMPLOYEE_ENDPOINT, produces = (MediaType.APPLICATION_JSON_VALUE))
  public @ResponseBody
  ResponseEntity<GetEmployeeResponse> getEmployee(@RequestParam(defaultValue = "", name = REQUESTPARAM_EMPLOYEE_ID) Long employeeId) {
    GetEmployeeResponse employee = employeeDelegate.getEmployee(employeeId);
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    return new ResponseEntity<>(employee, headers, HttpStatus.OK);
  }

}
