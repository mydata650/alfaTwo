package com.alfaTwo.model;

import java.util.Arrays;

public class PostRequestModel {
	private String token;
	private int[] scores;
	
	
	@Override
	public String toString() {
		return "PostRequestModel [token=" + token + ", scores=" + Arrays.toString(scores) + "]";
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public PostRequestModel(String token, int[] scores) {
		super();
		this.token = token;
		this.scores = scores;
	}
	public int[] getScores() {
		return scores;
	}
	public void setScores(int[] scores) {
		this.scores = scores;
	}
	public PostRequestModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
