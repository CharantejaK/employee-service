package com.telia.employee.controller;

import static com.telia.employee.controller.EmployeeController.GET_EMPLOYEE_ENDPOINT;
import static com.telia.employee.controller.EmployeeController.REQUESTPARAM_EMPLOYEE_ID;
import static com.telia.employee.controller.EmployeeController.SAVE_EMPLOYEE_ENDPOINT;
import static com.telia.employee.controller.IntegrationTestHelper.getSaveEmployeeRequest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.telia.employee.dto.GenericResponse;
import com.telia.employee.dto.GetEmployeeResponse;
import com.telia.employee.dto.SaveEmployeeRequest;
import java.net.URI;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;



/*
Starts the server on a random port and shoots the urls

https://spring.io/guides/gs/testing-web/

*/

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class HttpRequestTest {

  private static final String LOCALHOST_URL = "http://localhost:";

  private static final Long EMPLOYEE_ID = 456L;
  private static final String FIRST_NAME = "Steve";
  private static final String LAST_NAME = "Waugh";

  @LocalServerPort
  private int port;

  @Autowired
  TestRestTemplate restTemplate;


  @Test
  public void testSaveEmployeeDetails() throws Exception {
    String url = LOCALHOST_URL + port;

    URI saveEmployeeUri = UriComponentsBuilder.fromHttpUrl(url).path(SAVE_EMPLOYEE_ENDPOINT)
        .build()
        .toUri();

    Long employeeId = 456L;
    String firstName = "Steve";
    String lastName = "Waugh";

    SaveEmployeeRequest saveRequest = getSaveEmployeeRequest(employeeId, firstName, lastName);
    ResponseEntity<GenericResponse> saveEmployeeResponse = this.restTemplate.postForEntity(saveEmployeeUri,saveRequest, GenericResponse.class);

    assertThat(saveEmployeeResponse.getBody()).isNotNull();
    assertThat(saveEmployeeResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertEquals(saveEmployeeResponse.getBody().getSuccess(), Boolean.TRUE);

    URI getEmployeeUri = UriComponentsBuilder.fromHttpUrl(url).path(GET_EMPLOYEE_ENDPOINT).queryParam(REQUESTPARAM_EMPLOYEE_ID, employeeId)
        .build()
        .toUri();

    ResponseEntity<GetEmployeeResponse> getEmployeeResponse = this.restTemplate.getForEntity(getEmployeeUri, GetEmployeeResponse.class);
    assertThat(getEmployeeResponse.getBody()).isNotNull();
    assertThat(getEmployeeResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertEquals(getEmployeeResponse.getBody().getSuccess(), Boolean.TRUE);
    assertThat(getEmployeeResponse.getBody().getEmployee()).isNotNull();
    assertEquals(getEmployeeResponse.getBody().getEmployee().getFirstName(), firstName);
    assertEquals(getEmployeeResponse.getBody().getEmployee().getLastName(), lastName);

  }
}
