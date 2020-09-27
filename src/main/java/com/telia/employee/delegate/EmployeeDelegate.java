package com.telia.employee.delegate;

import com.telia.employee.dao.EmployeeRepository;
import com.telia.employee.dto.EmployeeDto;
import com.telia.employee.dto.GenericResponse;
import com.telia.employee.dto.GetEmployeeResponse;
import com.telia.employee.dto.SaveEmployeeRequest;
import com.telia.employee.entity.Employee;
import com.telia.employee.exception.BusinessException;
import com.telia.employee.mapper.EmployeeServiceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
@PropertySource("classpath:messages.properties")
public class EmployeeDelegate {

  @Autowired
  EmployeeRepository employeeRepository;

  @Autowired
  Environment env;

  @Autowired
  EmployeeServiceMapper mapper;

  @Transactional
  public GenericResponse saveEmployee(SaveEmployeeRequest employeeRequestDto) throws BusinessException {
    EmployeeDto employeeDto = employeeRequestDto.getEmployee();
    if (employeeDto == null || employeeDto.getEmployeeId() == null || StringUtils.isEmpty(employeeDto.getFirstName()) || StringUtils.isEmpty(employeeDto.getFirstName())) {
      throw new BusinessException(env.getProperty("employee.error.fieldsaremandatory"));
    }
    Employee employee = employeeRepository.findByEmployeeId(employeeDto.getEmployeeId());
    if (employee == null) {
      employee = mapper.map(employeeDto);
      employeeRepository.save(employee);
      GenericResponse response = new GenericResponse();
      response.setSuccess(Boolean.TRUE);
      return response;
    }
    throw new BusinessException(env.getProperty("employee.error.duplicateemployeeid"));
  }

  public GetEmployeeResponse getEmployee(Long employeeId) {
    GetEmployeeResponse response = new GetEmployeeResponse();
    if (employeeId == null) {
      throw new BusinessException(env.getProperty("employee.error.mandatoryemployeeid"));
    }
    Employee employee = employeeRepository.findByEmployeeId(employeeId);
    response.setEmployee(mapper.map(employee));
    response.setSuccess(Boolean.TRUE);
    return response;
  }
}
