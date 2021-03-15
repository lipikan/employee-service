package com.ing.employee.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ing.employee.dto.AddressDto;
import com.ing.employee.dto.EmployeeDto;
import com.ing.employee.exception.ResourceNotFoundException;
import com.ing.employee.models.Gender;
import com.ing.employee.service.EmployeeMgmtService;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class EmployeeControllerTest {

	private static final ObjectMapper om = new ObjectMapper();
	 
	@Autowired
	private TestRestTemplate restTemplate;

	@MockBean
	private EmployeeMgmtService mockEmployeeMgmtService;

	@Before
	public void init() throws ResourceNotFoundException {
		
		AddressDto address = AddressDto.builder()
							 .street("5 Morey Road")
							 .city("Epping")
							 .state("NSW")
							 .postCode(2121)
							 .build();
		EmployeeDto employee = EmployeeDto.builder()
							   .address(address)
							   .empId(12331L)
							   .firstName("Jason")
							   .lastName("Howard")
							   .gender(Gender.male)
							   .title("Mr")
							   .build();
		ResponseEntity<EmployeeDto> employeeResponseEntity = new ResponseEntity<>(employee, HttpStatus.OK);
		when(mockEmployeeMgmtService.getEmployeeDetails(any(Long.class))).thenReturn(employeeResponseEntity);
		
		EmployeeDto employeeForUpdate = EmployeeDto.builder()
				   .address(address)
				   .empId(12331L)
				   .firstName("Ryan")
				   .lastName("Howard")
				   .gender(Gender.male)
				   .title("Mr")
				   .build();
		ResponseEntity<EmployeeDto> updateEmployeeResponseEntity = new ResponseEntity<>(employeeForUpdate, HttpStatus.OK);
		when(mockEmployeeMgmtService.updateEmployee((any(Long.class)), any(EmployeeDto.class))).thenReturn(updateEmployeeResponseEntity);
	}

	
	@Test
	public void getEmployeeDetails_Success() throws Exception {
		
		String expected = "{\"empId\":12331,\"firstName\":\"Jason\",\"lastName\":\"Howard\",\"gender\":\"male\",\"title\":\"Mr\",\"address\":{\"street\":\"5 Morey Road\",\"city\":\"Epping\",\"state\":\"NSW\",\"postCode\":2121}}";

        ResponseEntity<String> response = restTemplate
                .withBasicAuth("user", "password")
                .getForEntity("/api/v1/employees/1", String.class);

        printJSON(response);

        assertEquals(MediaType.APPLICATION_JSON, response.getHeaders().getContentType());
        assertEquals(HttpStatus.OK, response.getStatusCode());

        JSONAssert.assertEquals(expected, response.getBody(), false);


	}

	@Test
	public void getEmployeeDetails_401() throws Exception {
		
		String expected = "{\"status\":401,\"error\":\"Unauthorized\",\"message\":\"\",\"path\":\"/api/v1/employees/1\"}";

        ResponseEntity<String> response = restTemplate
                .getForEntity("/api/v1/employees/1", String.class);

        printJSON(response);

        assertEquals(MediaType.APPLICATION_JSON, response.getHeaders().getContentType());
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());

        JSONAssert.assertEquals(expected, response.getBody(), false);

	}
	
	
	@Test
	public void updateEmployee_Success() throws Exception {
		
		JSONObject updateBody = new JSONObject();
        updateBody.put("firstName", "Ryan");
		
        ResponseEntity<EmployeeDto> response = restTemplate
                .withBasicAuth("admin", "password")
                .exchange("/api/v1/employees/1", HttpMethod.PATCH, getPostRequestHeaders(updateBody.toString()), EmployeeDto.class);

        printJSON(response);

        assertEquals(MediaType.APPLICATION_JSON, response.getHeaders().getContentType());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        EmployeeDto emp = response.getBody();
        assertEquals("Ryan", emp.getFirstName());
       


	}

	@Test
	public void updateEmployeeDetails_403() throws Exception {
		
		String expected = "{\"status\":403,\"error\":\"Forbidden\",\"message\":\"\",\"path\":\"/api/v1/employees/1\"}";

		JSONObject updateBody = new JSONObject();
        updateBody.put("firstName", "Ryan");
		
        ResponseEntity<String> response = restTemplate
                .withBasicAuth("user", "password")
                .exchange("/api/v1/employees/1", HttpMethod.PATCH, getPostRequestHeaders(updateBody.toString()), String.class);

        printJSON(response);

        assertEquals(MediaType.APPLICATION_JSON, response.getHeaders().getContentType());
        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());

        JSONAssert.assertEquals(expected, response.getBody(), false);

	}
	
	
	private static void printJSON(Object object) {
        String result;
        try {
            result = om.writerWithDefaultPrettyPrinter().writeValueAsString(object);
            System.out.println(result);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
	
	public HttpEntity getPostRequestHeaders(String jsonPostBody) {
        List acceptTypes = new ArrayList();
        acceptTypes.add(MediaType.APPLICATION_JSON);

        HttpHeaders reqHeaders = new HttpHeaders();
        reqHeaders.setContentType(MediaType.APPLICATION_JSON);
        reqHeaders.setAccept(acceptTypes);

        return new HttpEntity(jsonPostBody, reqHeaders);
    }

}
