package com.adiops.apigateway.dashboard.resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.adiops.apigateway.ApiGatewayApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApiGatewayApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DashboardResourceTests {
	@LocalServerPort
	private int port;

	TestRestTemplate restTemplate = new TestRestTemplate();
	HttpHeaders headers = new HttpHeaders();

	@Test
	public void testRetrieveRegions() throws Exception {
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/dashboard/regions"), HttpMethod.GET,
				entity, String.class);

		String expected = "[\r\n" + 
				"    \"Andaman and Nicobar Islands\",\r\n" + 
				"    \"Andhra Pradesh\",\r\n" + 
				"    \"Arunachal Pradesh\",\r\n" + 
				"    \"Assam\",\r\n" + 
				"    \"Bihar\",\r\n" + 
				"    \"Cases being reassigned to states\",\r\n" + 
				"    \"Chandigarh\",\r\n" + 
				"    \"Chhattisgarh\",\r\n" + 
				"    \"Dadar Nagar Haveli\",\r\n" + 
				"    \"Dadra and Nagar Haveli and Daman and Diu\",\r\n" + 
				"    \"Daman & Diu\",\r\n" + 
				"    \"Delhi\",\r\n" + 
				"    \"Goa\",\r\n" + 
				"    \"Gujarat\",\r\n" + 
				"    \"Haryana\",\r\n" + 
				"    \"Himachal Pradesh\",\r\n" + 
				"    \"Jammu and Kashmir\",\r\n" + 
				"    \"Jharkhand\",\r\n" + 
				"    \"Karnataka\",\r\n" + 
				"    \"Kerala\",\r\n" + 
				"    \"Ladakh\",\r\n" + 
				"    \"Madhya Pradesh\",\r\n" + 
				"    \"Maharashtra\",\r\n" + 
				"    \"Manipur\",\r\n" + 
				"    \"Meghalaya\",\r\n" + 
				"    \"Mizoram\",\r\n" + 
				"    \"Nagaland\",\r\n" + 
				"    \"Odisha\",\r\n" + 
				"    \"Puducherry\",\r\n" + 
				"    \"Punjab\",\r\n" + 
				"    \"Rajasthan\",\r\n" + 
				"    \"Sikkim\",\r\n" + 
				"    \"Tamil Nadu\",\r\n" + 
				"    \"Telangana\",\r\n" + 
				"    \"Telengana\",\r\n" + 
				"    \"Tripura\",\r\n" + 
				"    \"Unassigned\",\r\n" + 
				"    \"Uttar Pradesh\",\r\n" + 
				"    \"Uttarakhand\",\r\n" + 
				"    \"West Bengal\"\r\n" + 
				"]";

		JSONAssert.assertEquals(expected, response.getBody(), false);
	}

	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}

}
