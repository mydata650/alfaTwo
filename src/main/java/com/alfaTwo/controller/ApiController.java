package com.alfaTwo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.alfaTwo.dao.DataRepo;
import com.alfaTwo.model.Game;
import com.alfaTwo.model.GetResponseModel;
import com.alfaTwo.model.PostRequestModel;
import com.alfaTwo.model.PostResponseModel;
import com.alfaTwo.utility.HelpingMethods;
import com.alfaTwo.utility.TokenGeneration;


@RestController
public class ApiController {

	
	@Autowired
	private HelpingMethods helpingMethods;
	@Autowired
	private TokenGeneration genToken;
	
	@Autowired
	DataRepo repo;
	
	
	@GetMapping(path="/working")
	public String sayWorking() {
		return "working 0";
	}
	
	@GetMapping(path="/api/points")
	public ResponseEntity<GetResponseModel> getFramesPoint() {
		String token = genToken.generateToken();
		List<int[]> frames = helpingMethods.getFrames();
		int[] scores = helpingMethods.getScores(frames);
		String toSave = helpingMethods.getFrameString(scores);
		repo.save(new Game(1000, toSave, token));
		return ResponseEntity.ok(new GetResponseModel(token, frames, scores)); 
		
	}
	
	@PostMapping(path="/api/points")
	public ResponseEntity<?> validateFramesPoint(@RequestBody PostRequestModel prm) {
		try {
			Game g = repo.findBytokenValue(prm.getToken());
			if(g.getFramesValue().trim().equals(helpingMethods.getFrameString(prm.getScores()).trim())) {	
				return ResponseEntity.status(200).body(new PostResponseModel("True"));
			}
			else {
				return ResponseEntity.status(200).body(new PostResponseModel("false"));
			}
		}
		catch (Exception e) {
			return ResponseEntity.status(404).body(new PostResponseModel("false"));
		}
	}
}
