package com.alfaTwo.model;

import java.io.Serializable;

public class PostResponseModel implements Serializable {
	private static final long serialVersionUID = 1L;
	private String Success;

	public String getSuccess() {
		return Success;
	}
	public void setSuccess(String success) {
		Success = success;
	}
	public PostResponseModel(String success) {
		super();
		Success = success;
	}
	@Override
	public String toString() {
		return "PostResponseModel [Success=" + Success + "]";
	}

	
}
