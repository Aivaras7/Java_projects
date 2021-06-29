import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class LithiumPricing { // This class is responsible for setting right prices for each Lithium grade
	GenerateLithium lithium = new GenerateLithium();
	HashMap<Integer, Double> PricesHmap = new HashMap<Integer, Double>();
	private String x;

	public void setPrice() { // Setting price accordingly for each grade using hashmap
		lithium.generateSample();
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 3; j++) {
				int grades = lithium.getTray()[i][j];
				if (grades <= 9) {
					PricesHmap.put(grades, 300.00);
				} else if (grades >= 10 && grades <= 19) {
					PricesHmap.put(grades, 600.00);
				} else if (grades >= 20 && grades <= 29) {
					PricesHmap.put(grades, 900.00);
				} else {
					PricesHmap.put(grades, 1250.00);
				}
			}
		}

	}

	public void printPrice() { // Printing prices out with iteration
		Iterator it = PricesHmap.entrySet().iterator();
		System.out.println("Grade" + "       " + "Price - £");
		while (it.hasNext()) {
			Entry pairs = (Map.Entry) it.next();
			x = pairs.getKey() + " = " + pairs.getValue();
			System.out.println(x);
		}
	}

	public HashMap<Integer, Double> getPrice() { // returns a hashmap with prices
		PricesHmap.clear();
		lithium.generateSample();
		setPrice();
		return PricesHmap;

	}
}