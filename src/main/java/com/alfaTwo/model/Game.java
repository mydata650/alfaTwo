package com.alfaTwo.model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Game {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String tokenValue;
	private String framesValue;
	
	public int getId() {
		return id;
	}
	public Game() {
		super();
		// TODO Auto-generated constructor stub
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTokenValue() {
		return tokenValue;
	}
	public void setTokenValue(String tokenValue) {
		this.tokenValue = tokenValue;
	}
	public String getFramesValue() {
		return framesValue;
	}
	public void setFramesValue(String framesValue) {
		this.framesValue = framesValue;
	}
	@Override
	public String toString() {
		return "Game [id=" + id + ", tokenValue=" + tokenValue + ", framesValue=" + framesValue + "]";
	}
	public Game(int id, String framesValue, String tokenValue) {
		super();
		this.id = id;
		this.framesValue = framesValue;
		this.tokenValue = tokenValue;
	}
}
