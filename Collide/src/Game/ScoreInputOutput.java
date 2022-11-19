package Game;
import java.io.*;
import java.util.Scanner;
public class ScoreInputOutput {
	
	/*
	 * Method uses I/O by first receiving and inputting the player's final
	 * score into a text file, then it reads and returns the score within the text file
	 * pre: uses int parameters playerScore
	 * post: returns int parameter scoreInt
	 */
	public static int getScore(int playerScore, int level) throws IOException {
		if (level == 1) {
			PrintWriter scoreInput = new PrintWriter("ScoreTrackerLevel1.txt");
			scoreInput.println(playerScore); // Writes playerScore into the text file ScoreTrackerLevel1
			scoreInput.close();
			Scanner scoreOutput = new Scanner(new File("ScoreTrackerLevel1.txt"));
			String scoreString = scoreOutput.nextLine(); // Reads text file ScoreTracker and it's String value is
															// received by scoreString
			int scoreInt = Integer.parseInt(scoreString); // Converts scoreString into an int value
			scoreOutput.close();
			return scoreInt;
		} 
		else if (level == 2){
			PrintWriter scoreInput = new PrintWriter("ScoreTrackerLevel2.txt");
			scoreInput.println(playerScore); // Writes playerScore into the text file ScoreTrackerLevel2
			scoreInput.close();
			Scanner scoreOutput = new Scanner(new File("ScoreTrackerLevel2.txt"));
			String scoreString = scoreOutput.nextLine(); // Reads text file ScoreTracker and it's String value is
															// received by scoreString
			int scoreInt = Integer.parseInt(scoreString); // Converts scoreString into an int value
			scoreOutput.close();
			return scoreInt;
		}
		else {
			PrintWriter scoreInput = new PrintWriter("ScoreTrackerLevel3.txt");
			scoreInput.println(playerScore); // Writes playerScore into the text file ScoreTrackerLevel3
			scoreInput.close();
			Scanner scoreOutput = new Scanner(new File("ScoreTrackerLevel3.txt"));
			String scoreString = scoreOutput.nextLine(); // Reads text file ScoreTracker and it's String value is
															// received by scoreString
			int scoreInt = Integer.parseInt(scoreString); // Converts scoreString into an int value
			scoreOutput.close();
			return scoreInt;
		}
	}

}
