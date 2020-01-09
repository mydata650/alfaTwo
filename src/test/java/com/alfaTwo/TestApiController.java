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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.*;

import org.springframework.http.MediaType;
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
	
	private ResponseEntity<GetResponseModel> res ;
	private String token; 
	private int[] sums ;
	private PostRequestModel prm;
	private HttpHeaders headers;
	private HttpEntity<PostRequestModel> entity;
	private ResponseEntity<String> response;
	
	/**
	 * @author khurram
	 * @About_Test: ecah test has it own scope and considered independently
	 * 
	 */
	
	
	/**
	 * @Test: Get API is working 
	 * 
	 */
	@Test
	public void testGetApiIsWorking() throws Exception {
		res = this.trt.getForEntity("http://localhost:" +  port   + "/api/points", GetResponseModel.class);
		assertThat(res.getStatusCode(), equalTo(HttpStatus.OK));
	}

	/**
	 * @Test: Get method's response contains a token, which is not null 
	 * 
	 */
	@Test
	public void testGetResponseHasNonEmptyToken() throws Exception {
		res = this.trt.getForEntity("http://localhost:" +  port   + "/api/points", GetResponseModel.class);
		token = res.getBody().getToken();
		assertThat(token.isEmpty(), is(false));
	}
	
	/**
	 * @Test:Validate Get response such that number of dropped pins are not more than 10 
	 * 
	 */
	@Test
	public void testNumberOfDroppedPinsNotMorethan10() throws Exception {
		res = this.trt.getForEntity("http://localhost:" +  port   + "/api/points", GetResponseModel.class);
		boolean lessthan11= true;
		for(int[] frame: res.getBody().getFrames()) {
			if(frame[0] + frame[1] > 10) {lessthan11 = false;}		//-Supposed, every frame will have two values for simplicity, otherwise have to implement loop for size of array
		}
		assertThat(lessthan11, is(true));
	}
	
	
	/**
	 * @throws URISyntaxException 
	 * @Test: Post method response is 200, where token is valid and scores are correct 
	 * 
	 */
	@Test
	public void testTokenIsValidAndScoresAreCorrect() throws Exception {
		res = this.trt.getForEntity("http://localhost:" +  port   + "/api/points", GetResponseModel.class);
		prm = new PostRequestModel(res.getBody().getToken(), res.getBody().getScores());
		headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		entity = new HttpEntity<PostRequestModel>(prm, headers);
		response = this.trt.postForEntity("http://localhost:" +  port   + "/api/points", entity, String.class);
		assertThat(response.getStatusCodeValue(), is(200));
	}
	
	/**
	 * @throws URISyntaxException 
	 * @Test: Post response is 404, because token is invalid 
	 * 
	 */
	@Test
	public void testTokenIsInvalid() throws Exception {
		res = this.trt.getForEntity("http://localhost:" +  port   + "/api/points", GetResponseModel.class);
		token = res.getBody().getToken();
		String invalidToken = token.substring(0, token.length()-1);
		prm = new PostRequestModel(invalidToken, res.getBody().getScores());
		headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		entity = new HttpEntity<PostRequestModel>(prm, headers);
		response = this.trt.postForEntity("http://localhost:" +  port   + "/api/points", entity, String.class);
		assertThat(response.getStatusCodeValue(), is(404));
	}	
	
	/**
	 * @throws URISyntaxException 
	 * @Test: Post response is success=false, and ok(200), when token is valid but sum(scores) are incorrect 
	 * 
	 */
	@Test
	public void testTokenIsValidButSumIsIncorrect() throws Exception {
		res = this.trt.getForEntity("http://localhost:" +  port   + "/api/points", GetResponseModel.class);
		sums = res.getBody().getScores();
		sums[0] = -1;
		prm = new PostRequestModel(res.getBody().getToken(), sums);
		headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		entity = new HttpEntity<PostRequestModel>(prm, headers);
		response = this.trt.postForEntity("http://localhost:" +  port   + "/api/points", entity, String.class);
		assertThat(response.getStatusCodeValue(), is(200));
		assertThat(response.toString(), containsString("false"));
	}

}
