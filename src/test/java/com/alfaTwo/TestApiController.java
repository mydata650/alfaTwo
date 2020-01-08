package com.alfaTwo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.number.OrderingComparison.lessThan;

//import java.awt.PageAttributes.MediaType;
//import javax.ws.rs.core.MediaType;
import org.springframework.http.MediaType;
import java.net.URI;
import java.net.URISyntaxException;

import com.alfaTwo.model.*;



@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class TestApiController {
	@LocalServerPort
	private int port;
	@Autowired
	private TestRestTemplate trt;
	private MockMvc mockMvc;
	
	/**
	 * @Test: Get API is working 
	 * 
	 */
	@Test
	public void testGetApiIsWorking() throws Exception {
		ResponseEntity<String> res = this.trt.getForEntity("http://localhost:" +  port   + "/api/points", String.class);
		assertThat(res.getStatusCode(), equalTo(HttpStatus.OK));
	}

	/**
	 * @Test: Get response contains a token, which is not null 
	 * 
	 */
	@Test
	public void testGetResponseHasNonEmptyToken() throws Exception {
		ResponseEntity<GetResponseModel> res = this.trt.getForEntity("http://localhost:" +  port   + "/api/points", GetResponseModel.class);
		assertThat(res.getBody().getToken().isEmpty(), is(false));
	}
	
	/**
	 * @throws URISyntaxException 
	 * @Test: Get response contains a token, which is not null 
	 * 
	 */
	@Test
	public void testNumberOfPinsLessThan10() throws Exception {
		ResponseEntity<GetResponseModel> res = this.trt.getForEntity("http://localhost:" +  port   + "/api/points", GetResponseModel.class);
		PostRequestModel prm = new PostRequestModel(res.getBody().getToken(), res.getBody().getScores());
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		 HttpEntity<PostRequestModel> entity = new HttpEntity<PostRequestModel>(prm, headers);
		
		ResponseEntity<String> resP = this.trt.postForEntity("http://localhost:" +  port   + "/api/points", entity, String.class);
		System.out.println("1 >>>>>>>>>>>  "   + resP);
		assertThat(resP.getStatusCodeValue(), is(200));
	}
	

	@Test
	public void testTokenIsInvalid() throws Exception {
		ResponseEntity<GetResponseModel> res = this.trt.getForEntity("http://localhost:" +  port   + "/api/points", GetResponseModel.class);
		String token = res.getBody().getToken();
		token = token.substring(0, token.length()-1);
		PostRequestModel prm = new PostRequestModel(token, res.getBody().getScores());
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		 HttpEntity<PostRequestModel> entity = new HttpEntity<PostRequestModel>(prm, headers);
		
		ResponseEntity<String> resP = this.trt.postForEntity("http://localhost:" +  port   + "/api/points", entity, String.class);
		System.out.println("2 >>>>>>>>>>>  "   + resP);
		assertThat(resP.getStatusCodeValue(), is(404));
	}	
	
	
	
	

}
