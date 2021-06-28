import java.util.Random;

public class GenerateLithium { // This class is responsible for randomizing Lithium grades and making a tray
	int tray[][] = new int[5][3];
	private int grading = 0;
	private Random randomGenerator;

	/**
	 * Constructor for objects of class GenerateLithium
	 */

	public GenerateLithium() {

		randomGenerator = new Random();
	}

	public void generateSample() { // Generates a random tray
		for (int columns = 0; columns < 5; columns++) {
			for (int rows = 0; rows < 3; rows++) {
				grading = randomGenerator.nextInt(50);
				tray[columns][rows] = grading;

			}
		}
	}

	public void printTray() { // Prints the tray that was generated earlier

		for (int columns = 0; columns < 5; columns++) {
			for (int rows = 0; rows < 3; rows++) {
				System.out.print(tray[columns][rows] + "||");
			}
			System.out.println("");
		}
	}

	public int[][] getTray() { // Return statement for tray

		return tray;
	}
}