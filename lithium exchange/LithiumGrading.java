import java.util.ArrayList;
import java.util.List;

public class LithiumGrading { /*
								 * This class is responsible for adding generated Lithium grades into array and
								 * bubblesort
								 */
	private List<Integer> lowGrade = new ArrayList<>();
	private List<Integer> highGrade = new ArrayList<>();
	GenerateLithium lithium = new GenerateLithium();
	int i;
	int j;

	public void generateGrades() { // Calls a generate sample method then puts the generated grades into two arrays

		lithium.generateSample();

		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 3; j++) {
				int grades = lithium.getTray()[i][j];
				if (grades <= 25) { // if generated grade is less or equal to 25 it is put into lower grade
					lowGrade.add(grades);
				} else {
					highGrade.add(grades);
				}
			}
		}
	}

	public void sortingLithium(List<Integer> list) { // Sorting lithium grades from lowest to highest using bubblesort
		boolean done = false;

		while (!done) {
			done = true;
			for (int lst = list.size() - 1; lst > 1; lst--) {
				for (i = 0; i < lst; i++) {
					int n = i + 1;
					if (list.get(i) > list.get(n)) {
						int temp = list.get(i);
						list.set(i, list.get(n));
						list.set(n, temp);
						done = false;
					}
				}
			}
		}
	}

	public void printResult() { // Using the bubble sort method to sort lowGrade and HighGrade arrays

		System.out.println("Low ");
		sortingLithium(lowGrade);
		for (i = 0; i < lowGrade.size(); i++) {
			System.out.println(lowGrade.get(i));
		}
		System.out.println("High \n");
		sortingLithium(highGrade);
		for (int i = 0; i < highGrade.size(); i++) {
			System.out.println(highGrade.get(i));
		}
	}

}