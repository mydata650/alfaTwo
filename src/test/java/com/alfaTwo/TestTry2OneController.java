package com.alfaTwo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;

import static org.hamcrest.CoreMatchers.*;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class TestTry2OneController {
	@LocalServerPort
	private int port;
	
	@Autowired
	private TestRestTemplate trt;
	
	int value = 0;
	
	@Before
	public void setValue() {
		this.value = 10;
	}
	
	
	
	
	@Test
	public void sayHelloTestStatus() throws Exception {
		
		ResponseEntity<String> res = this.trt.getForEntity("http://localhost:" +  port   + "/hello", String.class);
		assertThat(res.getStatusCode(), equalTo(HttpStatus.OK));
	}
	
	@Test
	public void sayHelloTestStatusCode() throws Exception {
		//ResponseEntity<String> res = this.trt.getForEntity("http://localhost:" +  port   + "/hello", String.class);
		assertThat(value, is(10));
	}
	
	@Test
	public void sayHelloTestStatusCodeAgain() throws Exception {
		ResponseEntity<String> res = this.trt.getForEntity("http://localhost:" +  port   + "/hello", String.class);
		assertThat(res.getStatusCodeValue(), is(200));
	}
	
	
	
}
