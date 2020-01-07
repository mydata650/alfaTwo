package com.alfaTwo.utility;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class HelpingMethods {
	/**
	 * @return a random number between 1-10, zero is not included
	 * since 1.0
	 */
	public int getRandomNumberBelow10() {
		return new Random().nextInt((9 - 1) + 1) + 1;
	}
	
	/**
	 * @return a random number between a given range, zero is included
	 * since 1.0
	 */
	public int getRandomBetweenZeroToN(int max) {
		return new Random().nextInt((max - 0) + 1) + 0;
	}
	
	/**
	 * @return a list of bowling frames with valid number of dropped pins
	 * since 1.0
	 */
	public List<int[]> getFrames() {
		int noOfFrames = getRandomNumberBelow10();
		List<int[]> frames = new ArrayList<int[]>(noOfFrames);	
		for (int i= 0; i < noOfFrames; i++  ) {
			int turnOnePins = 0, turnTwoPins = 0;
			turnOnePins = getRandomBetweenZeroToN(10); 
			turnTwoPins = getRandomBetweenZeroToN(10 - turnOnePins);
			frames.add(new int[] {turnOnePins, turnTwoPins});
		}
		return frames;
	}
	
	/**
	 * @return a scores of received method
	 * since 1.0
	 */
	public int[] getSum() {
		int[] scores = new int[] {12, 34, 23, 45};
		return scores;
	}

	/**
	 * @return calculate and return total scores of the frames
	 * since 1.0
	 */
	public int[] getScores(List<int[]> frames) {
		int[] frameSum = new int[10];		
		int[] bounes = new int[10];
		int index = 0;
		
		for(int[] arr : frames) {
			for(int j = 0; j < arr.length ; j++) {
				for(int i = 0; i < index; i++) {
					if(bounes[i] > 0) {
						bounes[i] = bounes[i] - 1;
						frameSum[i] += arr[j];
					}
				}
				//-player has a strike
				if(j== 0 && arr[0] > 9) { 
					bounes[index] = 2;
					frameSum[index] = 10;
					if(index < 9) { break; }
				}
				//-player doesn't have strike
				else {
					if( j > 0) {
						//-player has spare
						if(arr[0] + arr[1] > 9 && index < 9  ) {bounes[index] = 1; frameSum[index] = 10;}
						//- player doesn't have spare
						else {
							frameSum[index] += arr[j];
						}
					}
					//-only first bowl of frame has thrown and it's not strike.
					else {
						bounes[index] = -1; 
						frameSum[index] += arr[j];
					}
				}
			}
			index++;
		}
		
		return addFrameScore(frames.size(), frameSum);
	}

	/**
	 * @return an array with total scores
	 * since 1.0
	 */
	public int[] addFrameScore(int noOfFrames, int[] individualFrameScores) {
		int totalScores = 0;		
		int[] scores = new int[noOfFrames]; 
		for(int i = 0; i < noOfFrames ; i++) {
			totalScores += individualFrameScores[i];
			scores[i] = totalScores;
		}
		return scores;
	}
	
	/**
	 * @return Frames vlaues in the form of string to save in database
	 * since 1.0
	 */
	public String getFrameString(int[] frames) {
		String res = "";
		for(int val : frames) {
			res += val + ":";
		}
		return res;
	}
	
}
