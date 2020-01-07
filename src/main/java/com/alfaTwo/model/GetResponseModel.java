package com.alfaTwo.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class GetResponseModel implements Serializable  {
	
	private static final long serialVersionUID = 1L;
	private String token;
	private List<int[]> frames;
	private int[] scores;
		
	public GetResponseModel(String token, List<int[]> frames, int[] scores) {
		super();
		this.token = token;
		this.frames = frames;
		this.scores = scores;
	}
	
	@Override
	public String toString() {
		return "GetResponseModel [token=" + token + ", frames=" + frames + ", scores=" + Arrays.toString(scores) + "]";
	}
	
	public String getToken() {
		return token;
	}
	
	public void setToken(String token) {
		this.token = token;
	}
	
	public List<int[]> getFrames() {
		return frames;
	}
	
	public void setFrames(List<int[]> frames) {
		this.frames = frames;
	}
	
	public int[] getScores() {
		return scores;
	}
	
	public void setScores(int[] scores) {
		this.scores = scores;
	}
}
