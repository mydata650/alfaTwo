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
	
	public int[] getScores(List<int[]> frames) {
//		List<int[]> frames = new ArrayList<int[]>(10);
//		frames.add(new int[] {3, 7});
//		frames.add(new int[] {10, 0});
//		frames.add(new int[] {8, 2});
//		frames.add(new int[] {8, 1});
//		frames.add(new int[] {10, 0});
//		frames.add(new int[] {3, 4});
//		frames.add(new int[] {7, 0});
//		frames.add(new int[] {5, 5});
//		frames.add(new int[] {3, 2});
//		frames.add(new int[] {2, 5});
		
//		[[3,7],[10,0],[8,2],[8,1],[10,0],[3,4],[7,0],[5,5],[3,2],[2,5]]
		
		int[] frameSum = new int[10];
		int[] scores = new int[10];
		int[] bounes = new int[10];
		
		//-TotalScore is not being used
		int index = 0, insertIndex = 0, totalScore = 0;
		String res = "";
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
					//totalScore += 10;
					if(index < 9) { break; }
				}
				//-player doesn't have strike
				else {
					//-if both bowls of the frame has come and score of the frame is 10 like [5 + 5]
					if( j > 0) {
						//-player has spare
						if(arr[0] + arr[1] > 9 && index < 9  ) {bounes[index] = 1; frameSum[index] = 10;}
						//- player doesn't have spare
						else {
							frameSum[index] += arr[j];
						}
						//totalScore += arr[j];
					}
					//-only first bowl of frame has thrown and it's not strike.
					else {
						bounes[index] = -1; 
						frameSum[index] += arr[j];
						//totalScore += arr[j];
					}
				}
			} // for-j
			index++;
		} //- for-i
		int[] response = new int[frames.size()]; 
		for(int i = 0; i < frames.size() ; i++) {
			totalScore += frameSum[i];
			response[i] = totalScore;
			res += scores[i] + "  :  "; 
		}
		return response;
	} //end of getScores
	
}
