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
	
	@GetMapping(path="/api/points")
	public ResponseEntity<GetResponseModel> getFramesPoint() {
		String token = genToken.generateToken();									/*Generating token*/
		List<int[]> frames = helpingMethods.getFrames();							/*Generating valid bowling frames*/
		int[] scores = helpingMethods.getScores(frames);							/*Calculating frames scores*/			
		String toSave = helpingMethods.setScoresToString(scores);					/*Converting scores into a string*/
		repo.save(new Game(1000, toSave, token));									/*Saving scores and token into database*/
		return ResponseEntity.ok(new GetResponseModel(token, frames, scores)); 		/*Returning JSON response with token, bowling frames and total scores*/
	}
	
	@PostMapping(path="/api/points")
	public ResponseEntity<PostResponseModel> validateFramesPoint(@RequestBody PostRequestModel prm) {
		try {
			Game savedGame = repo.findBytokenValue(prm.getToken());
			if(savedGame.getFramesValue().trim().equals(helpingMethods.setScoresToString(prm.getScores()).trim())) {
				return ResponseEntity.status(200).body(new PostResponseModel("True"));		//- Token is valid and scores are correct
			}
			else {
				return ResponseEntity.status(200).body(new PostResponseModel("false"));		//- Token is valid but scores are not correct
			}
		}
		catch (Exception e) {
			return ResponseEntity.status(404).body(new PostResponseModel("false"));			//- Token is not valid or not exist
		}
	}
}
