package com.cg.ecdm.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.cg.ecdm.entity.Employee;
import com.cg.ecdm.entity.ExpenseDetails;
import com.cg.ecdm.entity.Project;
import com.cg.ecdm.exceptions.UserNotFoundException;

@RestController
@RequestMapping("/claim")
public class ECDMController {

	@Autowired
	RestTemplate restTemplate;

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@RequestMapping("/createEmp")
	public ResponseEntity<Employee> createEmpData(@RequestBody Employee employee) {

		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setContentType(MediaType.APPLICATION_JSON);
		requestHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Employee> requestEntity = new HttpEntity<>(employee,
				requestHeaders);

		ResponseEntity<Employee> responseEntity = restTemplate.exchange(
				"http://employee-service/employee/create", HttpMethod.POST,
				requestEntity, Employee.class);
		System.out.println(responseEntity.getStatusCodeValue());
			if (responseEntity.getStatusCode().value() != 200) {
				throw new UserNotFoundException("employee already exists");
			}
		return responseEntity;

	}

	@RequestMapping("/getAllEmployees")
	public List<Employee> getEmployees() {
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.add("Accept", MediaType.APPLICATION_JSON_VALUE);

		ResponseEntity<List<Employee>> rateResponse = restTemplate.exchange(
				"http://employee-service/employee/readall", HttpMethod.GET,
				null, new ParameterizedTypeReference<List<Employee>>() {
				});
			if (rateResponse.getStatusCode().value() != 200) {
				throw new UserNotFoundException("No employees found");
			}
		List<Employee> rates = rateResponse.getBody();
		return rates;

	}

	@RequestMapping(value = "/getEmp/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Employee getEmployeesbyID(@PathVariable int id) {
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.add("Accept", MediaType.APPLICATION_JSON_VALUE);
		HttpEntity<Employee> requestEntity = new HttpEntity<>(requestHeaders);
		Employee response = null;
		restTemplate.exchange(
				"http://employee-service/employee/read/{employeeId}",
				HttpMethod.GET, requestEntity,
				new ParameterizedTypeReference<Employee>() {
				}, id).getBody();
			response = restTemplate.exchange(
					"http://employee-service/employee/read/{employeeId}",
					HttpMethod.GET, requestEntity,
					new ParameterizedTypeReference<Employee>() {
					}, id).getBody();
			if(response==null){
				throw new UserNotFoundException("No such id exists " + id);
			}

		return response;

	}

	@RequestMapping(value = "/updateEmp/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<Employee> updateEmployeebyID(@PathVariable int id,
			@RequestBody Employee employee) {
		final String uri = "http://employee-service/employee/modify/{employeeId}";
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setContentType(MediaType.APPLICATION_JSON);
		requestHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Employee> requestEntity = new HttpEntity<>(employee,
				requestHeaders);
		ResponseEntity<Employee> responseEntity = restTemplate.exchange(uri,
				HttpMethod.PUT, requestEntity, Employee.class, id);

		if (responseEntity.getStatusCode().value() != 200) {
			throw new UserNotFoundException("No such id exists " + id);
		}

		return responseEntity;

	}

	@RequestMapping(value = "/deleteEmp/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public Employee deleteEmployeebyID(@PathVariable int id) {
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.add("Accept", MediaType.APPLICATION_JSON_VALUE);
		HttpEntity<Employee> requestEntity = new HttpEntity<>(requestHeaders);
		Employee employee = null;
			employee = restTemplate.exchange(
					"http://employee-service/employee/delete/{employeeId}",
					HttpMethod.DELETE, requestEntity,
					new ParameterizedTypeReference<Employee>() {
					}, id).getBody();
			if(employee==null){
				throw new UserNotFoundException("No such id exists " + id);
			}
		return employee;

	}

	@RequestMapping("/createExpense")
	public ResponseEntity<ExpenseDetails> createExpenseData(
			@RequestBody ExpenseDetails employee) {

		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setContentType(MediaType.APPLICATION_JSON);
		requestHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<ExpenseDetails> requestEntity = new HttpEntity<>(employee,
				requestHeaders);

		ResponseEntity<ExpenseDetails> responseEntity = restTemplate.exchange(
				"http://expense-service/ecm/create", HttpMethod.POST,
				requestEntity, ExpenseDetails.class);
			if (responseEntity.getStatusCode().value() != 200) {
				throw new UserNotFoundException("Expense code already exists"
						+ employee.getExpenseCode());
			}
		return responseEntity;

	}

	@RequestMapping("/getExpenses")
	public List<ExpenseDetails> getExpenseDetails() {
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.add("Accept", MediaType.APPLICATION_JSON_VALUE);

		ResponseEntity<List<ExpenseDetails>> rateResponse = null;

			rateResponse = restTemplate.exchange("http://expense-service/ecm/",
					HttpMethod.GET, null,
					new ParameterizedTypeReference<List<ExpenseDetails>>() {
					});
			if (rateResponse.getStatusCode().value() != 200) {
				throw new UserNotFoundException("No expenses found");
			}
		List<ExpenseDetails> rates = rateResponse.getBody();
		return rates;

	}

	@RequestMapping(value = "/getExpense/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ExpenseDetails getDetailsbyID(@PathVariable int id) {
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.add("Accept", MediaType.APPLICATION_JSON_VALUE);
		HttpEntity<ExpenseDetails> requestEntity = new HttpEntity<>(
				requestHeaders);
		ExpenseDetails response = null;

			response = restTemplate.exchange(
					"http://expense-service/ecm/{expenseCode}", HttpMethod.GET,
					requestEntity,
					new ParameterizedTypeReference<ExpenseDetails>() {
					}, id).getBody();
			if(response==null){
				throw new UserNotFoundException("No such id exists " + id);
			}

		return response;

	}

	@RequestMapping(value = "/updateExpense/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<ExpenseDetails> updateDetailsbyID(
			@PathVariable int id, @RequestBody ExpenseDetails employee) {
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setContentType(MediaType.APPLICATION_JSON);
		requestHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<ExpenseDetails> requestEntity = new HttpEntity<>(employee,
				requestHeaders);
		ResponseEntity<ExpenseDetails> responseEntity = null;

			responseEntity = restTemplate.exchange(
					"http://expense-service/ecm/update/{expenseCode}",
					HttpMethod.PUT, requestEntity, ExpenseDetails.class, id);
			if (responseEntity.getStatusCode().value() != 200) {
				throw new UserNotFoundException("No such id exists to update "
						+ id);
			}
		return responseEntity;

	}

	@RequestMapping(value = "/deleteExpense/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public ExpenseDetails deleteDetailsbyID(@PathVariable int id) {
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.add("Accept", MediaType.APPLICATION_JSON_VALUE);
		HttpEntity<ExpenseDetails> requestEntity = new HttpEntity<>(
				requestHeaders);
		ExpenseDetails employee = null;
			employee = restTemplate.exchange(
					"http://expense-service/ecm/{expenseCode}",
					HttpMethod.DELETE, requestEntity,
					new ParameterizedTypeReference<ExpenseDetails>() {
					}, id).getBody();
			if(employee==null){
				throw new UserNotFoundException("No such id exists " + id);
			}

		return employee;

	}

	@RequestMapping("/addProject")
	public ResponseEntity<Project> addProjectData(@RequestBody Project project) {

		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setContentType(MediaType.APPLICATION_JSON);
		requestHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Project> requestEntity = new HttpEntity<>(project,
				requestHeaders);
		ResponseEntity<Project> responseEntity = null;
			responseEntity = restTemplate.exchange(
					"http://project-service/project/insert", HttpMethod.POST,
					requestEntity, Project.class);
			if (responseEntity.getStatusCode().value() != 200) {
				throw new UserNotFoundException("Project id already exists "
						+ project.getprojectCode());
			}

		return responseEntity;

	}

	@RequestMapping("/getProjects")
	public List<Project> getProjectDetails() {
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.add("Accept", MediaType.APPLICATION_JSON_VALUE);

		ResponseEntity<List<Project>> rateResponse = null;
			rateResponse = restTemplate.exchange(
					"http://project-service/project/getAll", HttpMethod.GET,
					null, new ParameterizedTypeReference<List<Project>>() {
					});
			if (rateResponse.getStatusCode().value() != 200) {
				throw new UserNotFoundException("Project id already exists ");
			}
		List<Project> project = rateResponse.getBody();
		return project;

	}

	@RequestMapping(value = "/getProject/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Project getProjectbyID(@PathVariable int id) {
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.add("Accept", MediaType.APPLICATION_JSON_VALUE);
		HttpEntity<ExpenseDetails> requestEntity = new HttpEntity<>(
				requestHeaders);
		Project project = null;
			project = restTemplate.exchange(
					"http://project-service/project/get/{id}", HttpMethod.GET,
					requestEntity, new ParameterizedTypeReference<Project>() {
					}, id).getBody();
			if(project==null){
				throw new UserNotFoundException("No such id exists " + id);
			}

		return project;

	}

	@RequestMapping(value = "/updateProject/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<Project> updateProjectDetailsbyID(
			@PathVariable int id, @RequestBody Project project) {
		final String uri = "http://project-service/project/update/{id}";
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setContentType(MediaType.APPLICATION_JSON);
		requestHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Project> requestEntity = new HttpEntity<>(project,
				requestHeaders);
		ResponseEntity<Project> responseEntity = null;

			responseEntity = restTemplate.exchange(uri, HttpMethod.PUT,
					requestEntity, Project.class, id);
			if (responseEntity.getStatusCode().value() != 200) {
				throw new UserNotFoundException("No such id exists " + id);
			}

		return responseEntity;

	}

	@RequestMapping(value = "/deleteProject/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public Project deleteProjectDetailsbyID(@PathVariable int id) {
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.add("Accept", MediaType.APPLICATION_JSON_VALUE);
		HttpEntity<Project> requestEntity = new HttpEntity<>(requestHeaders);
		Project project = null;
		project=restTemplate.exchange("http://project-service/project/delete/{id}",
					HttpMethod.DELETE, requestEntity,
					new ParameterizedTypeReference<Project>() {
					}, id).getBody();
			if(project==null){
				throw new UserNotFoundException("No such id exists " + id);
			}
		return project;

	}
}
