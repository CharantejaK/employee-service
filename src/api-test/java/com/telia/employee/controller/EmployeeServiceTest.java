package com.telia.employee.controller;

import static com.telia.employee.controller.EmployeeController.GET_EMPLOYEE_ENDPOINT;
import static com.telia.employee.controller.EmployeeController.REQUESTPARAM_EMPLOYEE_ID;
import static com.telia.employee.controller.EmployeeController.SAVE_EMPLOYEE_ENDPOINT;
import static com.telia.employee.controller.IntegrationTestHelper.getSaveEmployeeRequest;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

/**
 * Testing the controller without starting the server
 * @See
 * https://spring.io/guides/gs/testing-web/
 */

@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeServiceTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @Test
  public void saveEmployeeTest() throws Exception {
    Long employeeId = 123L;
    String firstName = "CHARAN TEJA";
    String lastName = "KUNINTY";
    getSaveRequest(employeeId, firstName, lastName)
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("success").value(Boolean.TRUE.toString()));

      fetchRequest(employeeId).andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("employee.firstName").value(firstName))
        .andExpect(jsonPath("employee.lastName").value(lastName));

  }


  private ResultActions getSaveRequest(Long employeId, String firstName, String lastName) throws Exception{
    return this.mockMvc.perform(post(SAVE_EMPLOYEE_ENDPOINT)
        .content(objectMapper.writeValueAsString(getSaveEmployeeRequest(employeId, firstName, lastName)))
        .contentType(MediaType.APPLICATION_JSON));
  }

  private ResultActions fetchRequest(Long employeId) throws Exception{
     return this.mockMvc.perform(get(GET_EMPLOYEE_ENDPOINT).param(REQUESTPARAM_EMPLOYEE_ID, employeId.toString()));

  }
}
