package com.alfaTwo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alfaTwo.model.GetResponseModel;
import com.alfaTwo.utility.HelpingMethods;

@RestController
public class ApiController {

	
	@Autowired
	private HelpingMethods helpingMethods;
	
	
	@GetMapping(path="/working")
	public String sayWorking() {
		return "working 0";
	}
	
	@GetMapping(path="/api/points")
	public ResponseEntity<GetResponseModel> getFramesPoint() {
		String token = "IAmToken";
		List<int[]> frames = helpingMethods.getFrames();
		int[] scores = helpingMethods.getScores(frames);
		return ResponseEntity.ok(new GetResponseModel(token, frames, scores)); 
	}
}
